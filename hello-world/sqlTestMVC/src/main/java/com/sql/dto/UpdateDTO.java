package com.sql.dto;

public class UpdateDTO {

    private String updateTable;
    private String updateColumn;
    private String updateValue;
    private String updateWhereIf;
    private String updateWhereValue;

    public String getUpdateTable() {
        return updateTable;
    }

    public void setUpdateTable(String updateTable) {
        this.updateTable = updateTable;
    }

    public String getUpdateColumn() {
        return updateColumn;
    }

    public void setUpdateColumn(String updateColumn) {
        this.updateColumn = updateColumn;
    }

    public String getUpdateValue() {
        return updateValue;
    }

    public void setUpdateValue(String updateValue) {
        this.updateValue = updateValue;
    }

    public String getUpdateWhereIf() {
        return updateWhereIf;
    }

    public void setUpdateWhereIf(String updateWhereIf) {
        this.updateWhereIf = updateWhereIf;
    }

    public String getUpdateWhereValue() {
        return updateWhereValue;
    }

    public void setUpdateWhereValue(String updateWhereValue) {
        this.updateWhereValue = updateWhereValue;
    }

    @Override
    public String toString() {
        return "UpdateDTO{" +
                "updateTable='" + updateTable + '\'' +
                ", updateColumn='" + updateColumn + '\'' +
                ", updateValue='" + updateValue + '\'' +
                ", updateWhereIf='" + updateWhereIf + '\'' +
                ", updateWhereValue='" + updateWhereValue + '\'' +
                '}';
    }
}
