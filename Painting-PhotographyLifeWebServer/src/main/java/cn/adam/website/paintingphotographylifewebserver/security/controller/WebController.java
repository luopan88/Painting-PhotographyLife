package cn.adam.website.paintingphotographylifewebserver.security.controller;

import cn.adam.website.paintingphotographylifewebserver.modle.Message;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyCode;
import cn.adam.website.paintingphotographylifewebserver.utils.EmailUtil;
import cn.adam.website.paintingphotographylifewebserver.utils.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@RestController
@Slf4j
@Validated
public class WebController {
//    private static String ERROR_MESSAGE = "SPRING_SECURITY_LAST_EXCEPTION";
    @Autowired
    private VerifyCodeUtil verifyCodeUtil;
    @Autowired
    private EmailUtil emailUtil;

    @GetMapping({"/", "/index"})
    public Message index(HttpSession session){
        AuthenticationException o = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Message message;
        if (!Objects.isNull(o)) {
            String m = o.getMessage();
            message = Message.error(m);
        }else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null &&
                    authentication.isAuthenticated() &&
                    !(authentication instanceof AnonymousAuthenticationToken)) {
//                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                message = Message.success("已登录");
            }else {
                message = Message.error("请登录");
            }
        }
        return message;
    }

    @GetMapping("/verify/image")
    public void verifyImage(HttpSession session, HttpServletResponse response) throws IOException {
        VerifyCode code = verifyCodeUtil.generateCode();
        session.setAttribute(VerifyCode.VERIFY_IMAGE_SESSION_KEY, code);
        BufferedImage image = verifyCodeUtil.generateImage(code);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @PostMapping("/verify/email")
    public Message sendEmailCode(
            @Email(regexp = EmailUtil.EMAIL_REGEXP, message = "请输入正确的邮箱地址")
            @RequestParam("email") String email,
                                    HttpSession session) {
        VerifyCode verifyCode = verifyCodeUtil.generateCode();
        Message message;
        try {
            emailUtil.sendEmailVerifyCode(verifyCode, email);
            session.setAttribute(VerifyCode.VERIFY_EMAIL_SESSION_KEY, verifyCode);
            message = Message.success("邮箱验证码发送成功！");
        } catch (MessagingException e) {
            message = Message.error("邮箱验证码发送失败！");e.printStackTrace();
        }
        return message;
    }
}
