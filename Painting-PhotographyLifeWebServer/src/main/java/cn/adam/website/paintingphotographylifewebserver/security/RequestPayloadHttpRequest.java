package cn.adam.website.paintingphotographylifewebserver.security;

import cn.adam.website.paintingphotographylifewebserver.utils.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

public class RequestPayloadHttpRequest extends HttpServletRequestWrapper {
    private RequestPayloadHttpRequest(HttpServletRequest request) throws IOException {
        super(request);
        json = RequestUtil.getPayloadJsonData(request);
    }
    private JSONObject json;

    @Override
    public String getParameter(String name) {
        String res = super.getParameter(name);
        if (StringUtils.isEmpty(res)){
            res = json.getString(name);
        }

        return res;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Set<String> set = json.keySet();
        set.addAll(Collections.list(super.getParameterNames()));
        return Collections.enumeration(set);
    }

    public JSONObject getParameterPayloadJson() {
        return json;
    }

    public static HttpServletRequest get(HttpServletRequest request) throws IOException {
        if (StringUtils.equalsIgnoreCase(RequestUtil.REQUEST_PAYLOAD_CONTENT_TYPE,
                request.getContentType())){
            return new RequestPayloadHttpRequest(request);
        }
        return request;
    }
}
