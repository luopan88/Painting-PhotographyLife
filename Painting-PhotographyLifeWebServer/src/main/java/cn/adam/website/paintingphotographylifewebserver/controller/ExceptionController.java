package cn.adam.website.paintingphotographylifewebserver.controller;

import cn.adam.website.paintingphotographylifewebserver.modle.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Message handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String[] mess = new String[constraintViolations.size()];
        int i = 0;
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            mess[i++] = constraintViolation.getMessage();
        }
        Message error = Message.error("输入不正确");
        error.setData(mess);

        return error;
    }
}
