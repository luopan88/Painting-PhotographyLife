package cn.adam.website.paintingphotographylifewebserver.modle;

import lombok.Getter;

@Getter
public class Message {
    private Integer status;
    private String info;
    private Integer timestamp;
    private Object data;

    public Message(int status, String info) {
        this.status = status;
        this.info = info;
        long timestamp = System.currentTimeMillis()/1000;
        this.timestamp = (int) timestamp;
    }

    public static Message error(String info){
        return new Message(-1, info);
    }

    public static Message success(String info){
        return new Message(1, info);
    }

    public Message setData(Object data) {
        this.data = data;
        return this;
    }
}
