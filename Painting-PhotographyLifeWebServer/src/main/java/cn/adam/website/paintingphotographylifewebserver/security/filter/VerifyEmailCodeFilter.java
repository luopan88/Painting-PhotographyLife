package cn.adam.website.paintingphotographylifewebserver.security.filter;

import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyCode;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyConfigurationProperties;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyEmailCodeFilter extends VerifyFilter {

    @Autowired
    public VerifyEmailCodeFilter(VerifyConfigurationProperties properties) {
        super(VerifyCode.VERIFY_EMAIL_SESSION_KEY, "邮箱", VerifyType.EMAIL, "emailcode");
        setVerifyUrls(properties);
    }
}
