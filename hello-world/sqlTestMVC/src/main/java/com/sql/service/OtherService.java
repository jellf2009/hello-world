package com.sql.service;

import com.sql.pojo.Goods;

public interface OtherService {

    Goods getGoodsById(Integer id);

    void addGoods(Goods goods);
}
