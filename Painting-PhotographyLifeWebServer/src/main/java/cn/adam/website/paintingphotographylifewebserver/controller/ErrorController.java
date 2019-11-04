package cn.adam.website.paintingphotographylifewebserver.controller;

import cn.adam.website.paintingphotographylifewebserver.modle.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {

    private final BasicErrorController basicErrorController;

    public ErrorController(ErrorAttributes errorAttributes, ServerProperties errorProperties) {
        super(errorAttributes, Collections.emptyList());
        this.basicErrorController = new BasicErrorController(errorAttributes, errorProperties.getError());
    }

    @RequestMapping
    public ResponseEntity<Message> myError(HttpServletRequest request) {
        ResponseEntity<Map<String, Object>> error = basicErrorController.error(request);
        Map<String, Object> body = error.getBody();
        assert body != null;
        Message message = new Message(
                (int) body.get("status"),
                body.get("message").toString()
        );

        return new ResponseEntity<>(message, getStatus(request));
    }

    @Override
    public String getErrorPath() {
        return basicErrorController.getErrorPath();
    }
}
