package com.sql.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.sql.dto.ResponseDTO;
import com.sql.dto.SelectDTO;
import com.sql.dto.UpdateDTO;
import com.sql.service.TestService;
import com.sql.util.JDBCUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Random;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private DruidDataSource dataSource;

    @Override
    public ResponseDTO execute(String sql) {
        ResponseDTO res = new ResponseDTO();
        res.setSql(sql);
        DruidPooledConnection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            pst = connection.prepareStatement(sql);
            if (sql.length() > 10 && sql.substring(0, 10).contains("select")) {
                resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    res.setCode("OK");
                } else {
                    res.setCode("ERROR");
                }
                int i = 1;
                while (resultSet.next()) {
                    i++;
                }
                System.out.println(resultSet.getRow());
                res.setMsg("" + i);
            } else {
                int i = pst.executeUpdate();
                if (i > 0) {
                    res.setCode("OK");
                    res.setMsg("" + i);
                } else {
                    res.setCode("error");
                }
            }
        } catch (Exception e) {
            res.setCode("error");
            res.setMsg(e.getMessage());
            e.printStackTrace();
            System.out.println("不好意思 出错了哈");
        } finally {
            JDBCUtils.release(connection, pst, resultSet);
        }
        return res;
    }


    @Override
    public ResponseDTO selectTest(SelectDTO selectDTO) {
        ResponseDTO res = new ResponseDTO();
        DruidPooledConnection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        String sql = "";
        try {
            if (StringUtils.isNotBlank(selectDTO.getSelectTable()) && StringUtils.isNotBlank(selectDTO.getWhereif()) &&
                    StringUtils.isNotBlank(selectDTO.getWhereValue()) && selectDTO.getCount() >0){
                switch (selectDTO.getSelectTable()) {
                    case "goods":
                        sql = "select id,name,price,create_time FROM  "+selectDTO.getSelectTable()+" WHERE  "+selectDTO.getWhereif()+" = "+
                                selectDTO.getWhereValue()+" limit ?,?";
                                connection = dataSource.getConnection();
                                pst = connection.prepareStatement(sql);
                                pst.setObject(1, 0);
                                pst.setObject(2, selectDTO.getCount());
                                res.setSql(sql.substring(0,sql.length()-3)+"0, "+selectDTO.getCount());
                                res.setCode("OK");
                                resultSet = pst.executeQuery();
                                int j = 0;
                                while (resultSet.next()) {
                                    j++;
                                }
                                res.setMsg("" + j);
                    break;
                    case "mysqlrecord":
                        sql = "select id,sqlText,create_time FROM  "+selectDTO.getSelectTable()+" WHERE  "+selectDTO.getWhereif()+" = "+
                                selectDTO.getWhereValue()+" limit ?,?";
                        connection = dataSource.getConnection();
                        pst = connection.prepareStatement(sql);
                        pst.setObject(1, 0);
                        pst.setObject(2, selectDTO.getCount());
                        res.setSql(sql.substring(0,sql.length()-3)+"0, "+selectDTO.getCount());
                        res.setCode("OK");
                        resultSet = pst.executeQuery();
                        int k = 0;
                        while (resultSet.next()) {
                            k++;
                        }
                        res.setMsg("" + k);
                    break;
                    case "ord_goods":
                        sql = "select id,oid,gid FROM  "+selectDTO.getSelectTable()+" WHERE  "+selectDTO.getWhereif()+" = "+
                                selectDTO.getWhereValue()+" limit ?,?";
                        connection = dataSource.getConnection();
                        pst = connection.prepareStatement(sql);
                        pst.setObject(1, 0);
                        pst.setObject(2, selectDTO.getCount());
                        res.setSql(sql.substring(0,sql.length()-3)+"0, "+selectDTO.getCount());
                        res.setCode("OK");
                        resultSet = pst.executeQuery();
                        int q = 0;
                        while (resultSet.next()) {
                            q++;
                        }
                        res.setMsg("" + q);
                    break;
                    case "t_ord":
                        sql = "select id, order_name, customer_name, price, create_time, update_time FROM "
                                +selectDTO.getSelectTable()+" WHERE  "+selectDTO.getWhereif()+" = "+
                                selectDTO.getWhereValue()+" limit ?,?";
                        connection = dataSource.getConnection();
                        pst = connection.prepareStatement(sql);
                        pst.setObject(1, 0);
                        pst.setObject(2, selectDTO.getCount());
                        res.setSql(sql.substring(0,sql.length()-3)+"0, "+selectDTO.getCount());
                        res.setCode("OK");
                        resultSet = pst.executeQuery();
                        int w = 0;
                        while (resultSet.next()) {
                            w++;
                        }
                        res.setMsg("" + w);
                    break;
                }
            }else {
                res.setCode("error");
                res.setMsg("您填入的参数不正确");
            }
        } catch (Exception e) {
            res.setCode("error");
            res.setMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, resultSet);
        }
        return res;

    }

    @Override
    public Long getSumCount(String tableName) {
        DruidPooledConnection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Long sumCount = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select count(1) from " + tableName +" where id between 1 and 3000000 limit 0,10;";
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                String s = resultSet.getString(1);
                sumCount = new Long(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, resultSet);
        }
        return sumCount;
    }

    @Override
    public ResponseDTO insertTest(int i, String insertTable) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (i < 1) {
            responseDTO = null;
            return responseDTO;
        }
        String sql = "";
        DruidPooledConnection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Long sumCount = null;
        try {
            connection = dataSource.getConnection();
            int count = 0;
            switch (insertTable) {
                case "goods":
                    sql = "insert into goods(name,price,create_time) VALUES (?,?,?)";
                    pst = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    for (int j = 0; j < i; j++) {
                        count++;
                        pst.setObject(1, "name" + j);
                        pst.setObject(2, j + i);
                        pst.setObject(3, new Date());
                        pst.addBatch();
                    }
                    break;
                case "mysqlrecord":
                    sql = "insert into mysqlrecord(sqlText,create_time) VALUES (?,?)";
                    pst = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    for (int j = 0; j < i; j++) {
                        count++;
                        pst.setObject(1, "sqlText" + j);
                        pst.setObject(2, j + i);
                        pst.addBatch();
                    }
                    break;
                case "ord_goods":
                    sql = "insert into ord_goods(gid,oid) VALUES (?,?)";
                    pst = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    Random r = new Random();
                    for (int j = 0; j < i; j++) {
                        count++;
                        pst.setObject(1, r.nextInt(99999));
                        pst.setObject(2, r.nextInt(99999));
                        pst.addBatch();
                    }
                    break;
                case "t_ord":
                    sql = "INSERT into t_ord(order_name,customer_name,price,create_time,update_time) values(?,?,?,?,?)";
                    pst = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    for (int j = 0; j < i; j++) {
                        count++;
                        pst.setObject(1, "order_name" + j);
                        pst.setObject(2, "customer_name" + j + i);
                        pst.setObject(3, j * 3.09 + 31 - i);
                        pst.setObject(4, new Date());
                        pst.setObject(5, new Date());
                        pst.addBatch();
                    }
                    break;
            }
            responseDTO.setSql(sql);
            pst.executeBatch();
            connection.commit();
            responseDTO.setCode("OK");
            responseDTO.setMsg("" + count);
        } catch (Exception e) {
            responseDTO.setCode("error");
            responseDTO.setMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, resultSet);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO updateTest(UpdateDTO updateDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String sql = "";
        DruidPooledConnection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Long sumCount = null;
        try{
            if (StringUtils.isNotBlank(updateDTO.getUpdateColumn()) && StringUtils.isNotBlank(updateDTO.getUpdateTable()) &&
                    StringUtils.isNotBlank(updateDTO.getUpdateValue()) && StringUtils.isNotBlank(updateDTO.getUpdateWhereIf()) &&
                    StringUtils.isNotBlank(updateDTO.getUpdateWhereValue())){
                sql = "update "+updateDTO.getUpdateTable()+" set "+updateDTO.getUpdateColumn()+" = ? where "+updateDTO.getUpdateWhereIf()+" = ?";
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                pst = connection.prepareStatement(sql);
                pst.setObject(1,updateDTO.getUpdateValue());
                pst.setObject(2,updateDTO.getUpdateWhereValue());
                responseDTO.setSql("update "+updateDTO.getUpdateTable()+" set "+updateDTO.getUpdateColumn()+" = "+updateDTO.getUpdateValue()+" where "+updateDTO.getUpdateWhereIf()+" = "+updateDTO.getUpdateWhereValue());
                int i = pst.executeUpdate();
                connection.commit();
                if (i > 0) {
                    responseDTO.setCode("OK");
                    responseDTO.setMsg("" + i);
                } else {
                    responseDTO.setCode("fail");
                    responseDTO.setMsg("没有匹配的更改");
                }
            }else {
                responseDTO.setCode("error");
                responseDTO.setMsg("您填入的参数不正确");
            }
        }catch (Exception e){
            responseDTO.setCode("error");
            responseDTO.setMsg(e.getMessage());
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,pst,resultSet);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO doubleSelectTest(Integer doubleValue, Integer doubleStart, Integer doubleSum,Integer joinNum) {
        ResponseDTO responseDTO = new ResponseDTO();
        String sql = "";
        DruidPooledConnection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Long sumCount = null;
        if (1 == joinNum){
            responseDTO.setSql("select a.id,a.name as gname,b.gid,b.oid as gprice from goods a left join ord_goods b on a.id=b.gid where a.id = "+
                    doubleValue+" limit "+doubleStart+", "+doubleSum);
        }else {
            responseDTO.setSql("select a.id,a.name as gname,b.gid,b.oid as gprice from goods a inner join ord_goods b on a.id=b.gid where a.id = "+
                    doubleValue+" limit "+doubleStart+", "+doubleSum);
        }
        try{
            if (doubleValue > 0 && doubleStart >= 0 && doubleSum > 0 ) {
                if (1 == joinNum){
                    sql = "select a.id,a.name as gname,b.gid,b.oid as gprice from goods a left join ord_goods b on a.id=b.gid where a.id = ? limit ?, ?";
                }else {
                    sql = "select a.id,a.name as gname,b.gid,b.oid as gprice from goods a inner join ord_goods b on a.id=b.gid where a.id = ? limit ?, ?";
                }

                connection = dataSource.getConnection();
                pst = connection.prepareStatement(sql);
                pst.setObject(1,doubleValue);
                pst.setObject(2,doubleStart);
                pst.setObject(3,doubleSum);
                resultSet = pst.executeQuery();
                int i = 0;
                while (resultSet.next()) {
                    i++;
                }
                responseDTO.setMsg("查询得到条数: " + i);
            }else {
                responseDTO.setCode("error");
                responseDTO.setMsg("您填入的参数不正确");
            }
        }catch (Exception e){
            responseDTO.setCode("error");
            responseDTO.setMsg(e.getMessage());
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,pst,resultSet);
        }
        return responseDTO;
    }


}
