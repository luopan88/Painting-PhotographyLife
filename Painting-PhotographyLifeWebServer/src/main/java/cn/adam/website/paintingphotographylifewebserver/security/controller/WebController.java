package cn.adam.website.paintingphotographylifewebserver.security.controller;

import cn.adam.website.paintingphotographylifewebserver.modle.Message;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyImageCode;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@RestController
@Slf4j
public class WebController {
//    private static String ERROR_MESSAGE = "SPRING_SECURITY_LAST_EXCEPTION";
    private final Producer producer;

    @Autowired
    public WebController(Producer producer) {
        this.producer = producer;
    }

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
        String code = producer.createText();
        session.setAttribute(VerifyImageCode.VERIFY_IMAGE_SESSION_KEY, new VerifyImageCode(code));
        BufferedImage image = producer.createImage(code);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
