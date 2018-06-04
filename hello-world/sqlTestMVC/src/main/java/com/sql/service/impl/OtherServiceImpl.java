package com.sql.service.impl;

import com.sql.dao.GoodsDao;
import com.sql.pojo.Goods;
import com.sql.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods getGoodsById(Integer id) {
        return goodsDao.findGoodsById(id);
    }

    @Override
    public void addGoods(Goods goods) {
        goodsDao.addGoods(goods);
    }
}
