package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public String showTest(HttpServletRequest request) {

        redisTemplate.opsForValue().set("keytest", "goggogoogogogogogoogoggoo");
        Object keytest = redisTemplate.opsForValue().get("keytest");
        System.out.println(keytest);
        return "test OK";
    }

}
