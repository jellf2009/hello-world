package com.sql.service;

import com.sql.dto.IndexDTO;
import com.sql.dto.ResponseDTO;
import com.sql.dto.UpdateDTO;

import java.util.List;

public interface TableService {

    List<IndexDTO> testGetIndex(String tableName);

    List<String> testGetAllTables(String databaseName);

    List<String> getAllColumns(String name);

}
