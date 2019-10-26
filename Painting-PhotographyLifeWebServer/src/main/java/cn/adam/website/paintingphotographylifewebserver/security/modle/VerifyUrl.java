package cn.adam.website.paintingphotographylifewebserver.security.modle;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpMethod;

@Data
@ToString
public class VerifyUrl {
    private String uri;
    private HttpMethod[] method;
    private VerifyType type;
}
