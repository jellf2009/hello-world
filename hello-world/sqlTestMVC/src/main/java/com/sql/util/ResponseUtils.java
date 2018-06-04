package com.sql.util;

import com.sql.dto.IndexDTO;
import com.sql.dto.TableDTO;
import com.sql.service.TableService;
import com.sql.service.TestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ResponseUtils {

    public static List<TableDTO> resPub(Model model, TestService testService, TableService tableService,String tableName){
        List<TableDTO> tableDTOS = new ArrayList<>();
        if (StringUtils.isBlank(tableName.trim())){
            tableName = "goods";
        }
        List<String> tables = new ArrayList<>();
        tables.add("goods");
        tables.add("mysqlrecord");
        tables.add("ord_goods");
        tables.add("t_ord");

        List<IndexDTO> indexDTOS = tableService.testGetIndex(tableName);

        if(tables != null && tables.size()>0){
            for (String name:tables){
                TableDTO tableDTO = new TableDTO();
//                long sumCount = testService.getSumCount(name);
//                List<String> columns = tableService.getAllColumns(name);
                tableDTO.setName(name);
//                tableDTO.setSumCount(sumCount);
//                tableDTO.setColumns(columns);
                tableDTOS.add(tableDTO);
            }

        }

        model.addAttribute("tableDTOS",tableDTOS);
        model.addAttribute("indexDTOS",indexDTOS);
        model.addAttribute("tableName",tableName);
        return tableDTOS;
    }
}
