package cn.adam.website.paintingphotographylifewebserver.security.modle;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="verify.image")
@Component
@Data
public class VerifyConfigurationProperties {
    private VerifyUrl[] verifyUrls;

    public void setVerifyUrls(String[] verifyUrls) {
        this.verifyUrls = getVerifyUrlsFromStr(verifyUrls);
    }

    private VerifyUrl[] getVerifyUrlsFromStr(String[] str) {
        VerifyUrl[] vs = new VerifyUrl[str.length];
        for (int index = 0; index < str.length; index++) {
            String v = str[index];
            String type = "image";
            String ms = "post";
            HttpMethod[] methods;
            String uri;
            int i = v.indexOf("]");
            if (i > -1){
                type = v.substring(v.indexOf("[") + 1, i);
            }
            int j = v.indexOf(":");
            if (j > -1){
                ms = v.substring(i+1, j);
            }
            uri = v.substring(j+1);
            String[] split = ms.split("/");
            int len = split.length;
            methods = new HttpMethod[len];
            for (int k = 0; k < len; k++) {
                System.out.println(split[k]);
                methods[k] = HttpMethod.resolve(split[k].toUpperCase());
                System.out.println(HttpMethod.resolve(split[k]));
            }
            VerifyUrl verifyUrl = new VerifyUrl();
            verifyUrl.setUri(uri);
            verifyUrl.setMethod(methods);
            verifyUrl.setType(VerifyType.get(type));

            vs[index] = verifyUrl;
        }
        return vs;
    }
}
