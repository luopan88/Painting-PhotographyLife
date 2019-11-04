package cn.adam.website.paintingphotographylifewebserver.security.filter;

import cn.adam.website.paintingphotographylifewebserver.security.exception.VerifyImageCodeException;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyCode;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyConfigurationProperties;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyType;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyUrl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class VerifyFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler =
            new SimpleUrlAuthenticationFailureHandler("/");

    private VerifyUrl[] verifyUrls;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final VerifyType TYPE;
    private final String VERIFY_CODE_SESSION_KEY;
    private final String NAME;
    private final String PARAMETER_NAME;

    public VerifyFilter(String VERIFY_CODE_SESSION_KEY, String NAME, VerifyType TYPE, String PARAMETER_NAME) {
        this.VERIFY_CODE_SESSION_KEY = VERIFY_CODE_SESSION_KEY;
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.PARAMETER_NAME = PARAMETER_NAME;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        if(pathMatch(httpServletRequest)){
            try {
                validate(httpServletRequest);
            }catch (VerifyImageCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean pathMatch(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod().toUpperCase();
        for (VerifyUrl v : verifyUrls) {
            if (TYPE.equals(v.getType()) && antPathMatcher.match(v.getUri(), uri)){
                for (HttpMethod m : v.getMethod()) {
                    if (m.matches(method)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException, VerifyImageCodeException {
        HttpSession session = request.getSession();
        VerifyCode code = (VerifyCode) session.getAttribute(VERIFY_CODE_SESSION_KEY);
        String reqcode = ServletRequestUtils.getStringParameter(request, PARAMETER_NAME);

        if(StringUtils.isBlank(reqcode)){
            throw new VerifyImageCodeException(NAME+"验证码值不能为空");
        }
        if (code == null){
            throw new VerifyImageCodeException(NAME+"验证码不存在");
        }
        if (code.isExpried()){
            session.removeAttribute(VERIFY_CODE_SESSION_KEY);
            throw new VerifyImageCodeException(NAME+"验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code.getCode(), reqcode)){
            throw new VerifyImageCodeException(NAME+"验证码不匹配");
        }
        session.removeAttribute(VERIFY_CODE_SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public VerifyUrl[] getVerifyUrls() {
        return verifyUrls;
    }

    public void setVerifyUrls(VerifyConfigurationProperties properties) {
        this.verifyUrls = properties.getVerifyUrls();
    }
}
