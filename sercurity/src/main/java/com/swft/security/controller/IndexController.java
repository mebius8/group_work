package com.swft.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author JiangTeJie
 * @since 2020/5/7 19:25
 */
@Controller
public class IndexController {

    private static final String PAGE_PREFIX = "index";

    @GetMapping("index")
    public String index(){
        return PAGE_PREFIX+"/"+PAGE_PREFIX;
    }
}
