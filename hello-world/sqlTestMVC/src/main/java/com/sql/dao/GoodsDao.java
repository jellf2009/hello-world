package com.sql.dao;

import com.sql.pojo.Goods;

public interface GoodsDao {

    Goods findGoodsById(Integer id);

    void addGoods(Goods goods);

    Goods updateGoods(Goods goods);


}
