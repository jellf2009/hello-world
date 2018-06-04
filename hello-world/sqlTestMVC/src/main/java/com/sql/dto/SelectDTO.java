package com.sql.dto;

public class SelectDTO {

    private String selectTable;
    private Integer count;
    private String whereif;
    private String whereValue;

    public String getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(String selectTable) {
        this.selectTable = selectTable;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getWhereif() {
        return whereif;
    }

    public void setWhereif(String whereif) {
        this.whereif = whereif;
    }

    public String getWhereValue() {
        return whereValue;
    }

    public void setWhereValue(String whereValue) {
        this.whereValue = whereValue;
    }

    @Override
    public String toString() {
        return "SelectDTO{" +
                "selectTable='" + selectTable + '\'' +
                ", count=" + count +
                ", whereif='" + whereif + '\'' +
                ", whereValue='" + whereValue + '\'' +
                '}';
    }
}
