package cn.adam.website.paintingphotographylifewebserver.controller;

import cn.adam.website.paintingphotographylifewebserver.modle.Message;
import cn.adam.website.paintingphotographylifewebserver.modle.User;
import cn.adam.website.paintingphotographylifewebserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/regist")
    public Message regist(@Validated User user){
        userService.regist(user);
        return Message.success("注册成功");
    }
}
