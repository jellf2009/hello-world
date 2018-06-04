package com.sql.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.sql.dto.IndexDTO;
import com.sql.service.TableService;
import com.sql.util.JDBCUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private DruidDataSource dataSource;
    @Value("${jdbc.url}")
    private String url;

    DruidPooledConnection connection = null;
    PreparedStatement pst = null;
    ResultSet resultSet = null;


    @Override
    public List<IndexDTO> testGetIndex(String tableName) {
        List<IndexDTO> indexDTOList = new ArrayList<>();
        try{
            if(StringUtils.isNotBlank(tableName.trim())){
                connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("show index from "+tableName);
                while (resultSet.next()){
                    IndexDTO indexDTO = new IndexDTO();
                    indexDTO.setTableName(resultSet.getString("Table"));
                    indexDTO.setNon_unique(resultSet.getString("Non_unique"));
                    indexDTO.setKey_name(resultSet.getString("Key_name"));
                    indexDTO.setSeq_in_index(resultSet.getString("Seq_in_index"));
                    indexDTO.setColumn_name(resultSet.getString("Column_name"));
                    indexDTO.setCollation(resultSet.getString("Collation"));
                    indexDTO.setCardinlity(resultSet.getString("Cardinality"));
                    indexDTO.setSub_part(resultSet.getString("Sub_part"));
                    indexDTO.setPacked(resultSet.getString("Packed"));
                    indexDTO.setIsNull(resultSet.getString("Null"));
                    indexDTO.setIndexType(resultSet.getString("Index_type"));
                    indexDTOList.add(indexDTO);
                }

                if (statement != null){
                    statement.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,pst,resultSet);
        }
        return indexDTOList;
    }

    @Override
    public List<String> testGetAllTables(String databaseName) {
        String dataName = url.substring(0, url.indexOf("?")).substring(url.lastIndexOf("/")+1);
        List<String> tables = new ArrayList<>();
        String sql = "select table_name from information_schema.tables where table_schema='"+dataName+"'";
        try {
            connection = dataSource.getConnection();
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()){
                tables.add(resultSet.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,pst,resultSet);
        }
        return tables;
    }

    @Override
    public List<String> getAllColumns(String tableName) {
        String dataName = url.substring(0, url.indexOf("?")).substring(url.lastIndexOf("/")+1);
        List<String> columns = new ArrayList<>();
        String sql = "select column_name from information_schema.columns where table_name='"+tableName+"' and table_schema='"+dataName+"'";
        try {
            connection = dataSource.getConnection();
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()){
                columns.add(resultSet.getString("column_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,pst,resultSet);
        }
        return columns;
    }
}
