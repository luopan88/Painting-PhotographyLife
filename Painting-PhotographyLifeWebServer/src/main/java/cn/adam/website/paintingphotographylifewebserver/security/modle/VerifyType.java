package cn.adam.website.paintingphotographylifewebserver.security.modle;

public enum  VerifyType {
    IMAGE, EMAIL;

    public static VerifyType get(String type){
        return valueOf(type.toUpperCase());
    }
}
