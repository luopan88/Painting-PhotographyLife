package cn.adam.website.paintingphotographylifewebserver.security.modle;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VerifyCode implements Serializable {
    private static final long serialVersionUID = 5177267097256650774L;
    public static String VERIFY_IMAGE_SESSION_KEY = "VERIFY_IMAGE_SESSION_KEY";
    public static String VERIFY_EMAIL_SESSION_KEY = "VERIFY_EMAIL_SESSION_KEY";

    private String code;
    private final static int DEFAULT_MIN = 30;
    private int min;
    private LocalDateTime expirationDate;

    public VerifyCode(String code){
        this(code, DEFAULT_MIN);
    }

    public VerifyCode(String code, int min){
        this.min = min;
        this.code = code;
        this.expirationDate = LocalDateTime.now().plusMinutes(min);
    }

    public boolean isExpried() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
