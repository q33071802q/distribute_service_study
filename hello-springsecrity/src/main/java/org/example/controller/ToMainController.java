package org.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("")
public class ToMainController {
    @PostMapping("/toMain")
    @PreAuthorize("hasPermission('permission1','permission1')")
    public String toMain(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetails){
            System.out.println("permission");
        }
        return "redirect:main.html"; //跳转登录成功
    }
}
