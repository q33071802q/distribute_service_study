package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {
    @RequestMapping("/world")
    public String world(){
        return "恭喜你登录成功";
    }
}
