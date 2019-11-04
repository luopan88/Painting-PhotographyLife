package cn.adam.website.paintingphotographylifewebserver.utils;

import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtil {
    public static final String EMAIL_REGEXP = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmailVerifyCode(VerifyCode code, String to) throws MessagingException {
        String body = "<div style=\"width: 300px; margin: auto;\">" +
                "<h1 style=\"border-radius: 5px 5px 0 0; margin: 0; color: #F9FDFF; background-color: #464646; padding: 10px 20px;border-bottom: #FFF 1px solid;\">绘影生活</h1>" +
                "<div style=\"border-radius: 0 0 5px 5px; background-color: rgba(70,70,70,.7); padding: 10px 20px;\">" +
                "<h3 style=\"margin: 0; color: #66C0F4;\">尊敬的用户您好!</h3>" +
                "<p style=\"color: #F9FDFF;\">以下是发自绘影生活的邮箱验证码，有效期为30分钟:</p>" +
                "<h2 style=\"margin: 0; color: #66C0F4;border: #4D4B48 1px dotted;width: 110px;padding: 5px;\">" +
                    code.getCode()
                    + "</h2>" +
                "<p style=\"font-size: 12px; color: #F9FDFF;\">若非本人操作，请不予理睬。</p>" +
                "</div>" +
                "</div>";

        sendEmail(to, body);
    }

    public void sendEmail(String to, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("绘影生活");
        helper.setText(text, true);
        mailSender.send(message);
    }
}
