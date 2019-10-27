package cn.adam.website.paintingphotographylifewebserver.security.filter;

import cn.adam.website.paintingphotographylifewebserver.security.RequestPayloadHttpRequest;
import cn.adam.website.paintingphotographylifewebserver.security.exception.VerifyImageCodeException;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyConfigurationProperties;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyImageCode;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyType;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyUrl;
import cn.adam.website.paintingphotographylifewebserver.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
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
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
public class VerifyImageCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler =
            new SimpleUrlAuthenticationFailureHandler("/");

    private VerifyUrl[] verifyUrls;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        httpServletRequest = RequestPayloadHttpRequest.get(httpServletRequest);

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
            if (VerifyType.IMAGE.equals(v.getType()) && antPathMatcher.match(v.getUri(), uri)){
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
        VerifyImageCode code = (VerifyImageCode) session.getAttribute(VerifyImageCode.VERIFY_IMAGE_SESSION_KEY);
        String imgcode = ServletRequestUtils.getStringParameter(request, "imgcode");

        if(StringUtils.isBlank(imgcode)){
            throw new VerifyImageCodeException("验证码值不能为空");
        }
        if (code == null){
            throw new VerifyImageCodeException("验证码不存在");
        }
        if (code.isExpried()){
            session.removeAttribute(VerifyImageCode.VERIFY_IMAGE_SESSION_KEY);
            throw new VerifyImageCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code.getCode(), imgcode)){
            throw new VerifyImageCodeException("验证码不匹配");
        }
        session.removeAttribute(VerifyImageCode.VERIFY_IMAGE_SESSION_KEY);
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

    @Autowired
    public void setVerifyUrls(VerifyConfigurationProperties properties) {
        this.verifyUrls = properties.getVerifyUrls();
    }
}
