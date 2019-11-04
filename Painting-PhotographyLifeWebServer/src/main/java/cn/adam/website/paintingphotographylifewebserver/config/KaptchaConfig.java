package cn.adam.website.paintingphotographylifewebserver.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

@Configuration
@Slf4j
public class KaptchaConfig {
    private Properties kaptchaProp = new Properties();
    public KaptchaConfig() throws Exception {

        try (Reader r = new FileReader(ResourceUtils.getFile("classpath:kaptcha.properties"))){
            kaptchaProp.load(r);
        } catch (FileNotFoundException e) {
            log.error("kaptcha配置文件不存在", e);
            throw e;
        } catch (IOException e) {
            log.error("kaptcha配置文件读取出错", e);
            throw e;
        }
    }
    @Bean
    public Producer config(){
        Config config = new Config(kaptchaProp);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
