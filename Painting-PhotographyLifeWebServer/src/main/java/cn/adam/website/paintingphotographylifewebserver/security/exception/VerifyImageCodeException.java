package cn.adam.website.paintingphotographylifewebserver.security.exception;


import org.springframework.security.core.AuthenticationException;

public class VerifyImageCodeException extends AuthenticationException {

    private static final long serialVersionUID = 8952098109156918882L;

    public VerifyImageCodeException(String explanation) {
        super(explanation);
    }
}
