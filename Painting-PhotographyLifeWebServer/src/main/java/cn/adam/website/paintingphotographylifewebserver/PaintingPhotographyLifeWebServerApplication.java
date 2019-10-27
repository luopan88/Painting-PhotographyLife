package cn.adam.website.paintingphotographylifewebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan("cn.adam.website.paintingphotographylifewebserver.**")
public class PaintingPhotographyLifeWebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaintingPhotographyLifeWebServerApplication.class, args);
    }

}
