package cn.adam.website.paintingphotographylifewebserver.security.filter;

import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyCode;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyConfigurationProperties;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerifyImageCodeFilter extends VerifyFilter {

    public VerifyImageCodeFilter() {
        super(VerifyCode.VERIFY_IMAGE_SESSION_KEY, "图形", VerifyType.IMAGE, "imgcode");
    }

    @Override
    @Autowired
    public void setVerifyUrls(VerifyConfigurationProperties properties) {
        super.setVerifyUrls(properties);
    }
}
