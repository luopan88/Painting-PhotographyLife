package cn.adam.website.paintingphotographylifewebserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestUtil {
    public static final String FORM_DATA_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String REQUEST_PAYLOAD_CONTENT_TYPE = "application/json;charset=utf-8";

    public static JSONObject getPayloadJsonData(HttpServletRequest request) throws IOException {
        String str = IOUtils.toString(request.getReader());
        return JSON.parseObject(str);
    }
}
