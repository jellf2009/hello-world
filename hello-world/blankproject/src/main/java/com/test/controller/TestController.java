package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String showTest(HttpServletRequest request) {
        System.out.println(request.getLocalAddr());
        return request.getRemoteAddr();
    }

}
