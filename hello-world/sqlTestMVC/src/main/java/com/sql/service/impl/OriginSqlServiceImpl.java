package com.sql.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.sql.pojo.Goods;
import com.sql.service.OriginSqlService;
import com.sql.util.JDBCUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class OriginSqlServiceImpl implements OriginSqlService {

    @Autowired
    private DruidDataSource dataSource2;


    @Override
    public Goods findGoodsById(Integer id) {
        Goods goods = new Goods();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try{
            String sql = "select * from goods where id = ?";
            connection = dataSource2.getConnection();
            pst = connection.prepareStatement(sql);
            pst.setObject(1,43);
            rst = pst.executeQuery();
            while (rst.next()){
                Field[] fields = Goods.class.getFields();
                for (Field f: fields) {
                    f.setAccessible(true);
                    f.set(goods, rst.getString(f.getName()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,pst,rst);
        }
        return goods;
    }
}
