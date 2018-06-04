package com.sql.test;

import com.sql.util.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class LogTest {

    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://106.14.75.147/test?characterEncoding=utf8&useSSL=false", "proxy", "proxy");

//            pst = connection.prepareStatement("update goods set name = ? where id = ?");
            pst = connection.prepareStatement("INSERT INTO t1(name,age) VALUES(?,?)");
            Random random = new Random();
            for (int i = 0; i < 1000 ; i++) {
                pst.setObject(1, "李四" +random.nextInt(233));
                pst.setObject(2, random.nextInt(89));
                pst.addBatch();
            }

            pst.executeBatch();
            int i = pst.executeUpdate();


            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception f) {
                f.printStackTrace();
            }
        } finally {
            JDBCUtils.release(connection, pst, resultSet);
        }


//        ResultSet resultSet = pst.executeQuery();
//        while (resultSet.next()) {
//            String id = resultSet.getString("id");
//            System.out.println(id);
//        }


    }
}
