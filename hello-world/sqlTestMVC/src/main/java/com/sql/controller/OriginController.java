package com.sql.controller;

import com.sql.pojo.Goods;
import com.sql.service.OriginSqlService;
import com.sql.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class OriginController {

    @Autowired
    private OriginSqlService originSqlService;
    @Autowired
    private OtherService otherService;

    @RequestMapping(value = "/goOriginSql")
    public ModelAndView showOriginSqlTest() {
        Goods goods = originSqlService.findGoodsById(3);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/originsqltest");
        modelAndView.addObject("ordi");
        return modelAndView;
    }


    @RequestMapping(value = "/otherTest")
    @ResponseBody
    public Goods showOtherTest() {
        Goods goods = otherService.getGoodsById(42379);
        Goods goods23 = new Goods();
        goods23.setName("符v");
        goods23.setCreate_time(new Date());
        goods23.setPrice(34);
        otherService.addGoods(goods23);
        return goods;
    }

}
