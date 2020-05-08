package com.swft.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author JiangTeJie
 * @since 2020/5/7 17:13
 */
@Controller
public class LoginController {

    private static final String PAGE_PREFIX = "login";

    @GetMapping("/login")
    public String login(){
        return PAGE_PREFIX+"/"+PAGE_PREFIX;
    }
}
