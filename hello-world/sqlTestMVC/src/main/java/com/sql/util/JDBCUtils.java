package com.sql.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.DruidWebUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        InputStream stream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc/jdbc.properties");
        properties.load(stream);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getProperty("dirver"));
        druidDataSource.setUrl(properties.getProperty("url"));
        druidDataSource.setUsername(properties.getProperty("username"));
        druidDataSource.setPassword(properties.getProperty("password"));
        druidDataSource.setMaxActive(1);
        return druidDataSource.getConnection();
    }


    public static void release(Connection connection, PreparedStatement pst, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
