package com.lxl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(value = "/index.htm")
    public String index(HttpServletRequest request) {
        System.out.println(request.getContextPath());
        return "index";
    }
}
