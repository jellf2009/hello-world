package com.sql.dto;

import java.io.Serializable;
import java.util.List;

public class TableDTO implements Serializable {


    private static final long serialVersionUID = -3318251962267455061L;

    private String name;

    private List<String> columns;

    private Long sumCount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public Long getSumCount() {
        return sumCount;
    }

    public void setSumCount(Long sumCount) {
        this.sumCount = sumCount;
    }
}
