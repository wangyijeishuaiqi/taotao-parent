package com.cskt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by
 * 白夜行 on 2018/4/15.
 */
@Controller
public class indexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
