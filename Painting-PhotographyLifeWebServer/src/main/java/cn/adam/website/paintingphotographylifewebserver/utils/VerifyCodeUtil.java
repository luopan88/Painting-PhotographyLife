package cn.adam.website.paintingphotographylifewebserver.utils;

import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyCode;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
public class VerifyCodeUtil {
    @Autowired
    private Producer producer;
    public VerifyCode generateCode(){
        String code = producer.createText();
        return new VerifyCode(code);
    }

    public BufferedImage generateImage(VerifyCode code){
        return generateImage(code.getCode());
    }
    public BufferedImage generateImage(String code){
        return producer.createImage(code);
    }
}
