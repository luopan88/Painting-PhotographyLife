package cn.adam.website.paintingphotographylifewebserver.security.modle;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VerifyImageCode implements Serializable {
    private static final long serialVersionUID = 5177267097256650774L;
    public static String VERIFY_IMAGE_SESSION_KEY = "VERIFY_IMAGE_SESSION_KEY";

    private String code;
    private LocalDateTime expirationDate;

    public VerifyImageCode(String code){
        this(code, 30);
    }

    public VerifyImageCode(String code, int t){
        this.code = code;
        this.expirationDate = LocalDateTime.now().plusMinutes(t);
    }

    public boolean isExpried() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
