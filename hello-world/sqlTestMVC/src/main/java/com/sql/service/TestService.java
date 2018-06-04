package com.sql.service;

import com.sql.dto.ResponseDTO;
import com.sql.dto.SelectDTO;
import com.sql.dto.UpdateDTO;

public interface TestService {
    public ResponseDTO execute(String sql);

    public ResponseDTO selectTest(SelectDTO selectDTO);

    Long getSumCount(String tableName);

    ResponseDTO insertTest(int i, String insertTable);

    ResponseDTO updateTest(UpdateDTO updateDTO);

    ResponseDTO doubleSelectTest(Integer doubleValue, Integer doubleStart, Integer doubleSum,Integer joinNum);
}
