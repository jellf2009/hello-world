package com.sql.dto;

public class IndexDTO {

    private String tableName;
    private String non_unique;
    private String key_name;
    private String seq_in_index;
    private String column_name;
    private String collation;
    private String cardinlity;
    private String sub_part;
    private String packed;
    private String isNull;
    private String indexType;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public String getSeq_in_index() {
        return seq_in_index;
    }

    public void setSeq_in_index(String seq_in_index) {
        this.seq_in_index = seq_in_index;
    }

    public String getCardinlity() {
        return cardinlity;
    }

    public void setCardinlity(String cardinlity) {
        this.cardinlity = cardinlity;
    }

    public String getSub_part() {
        return sub_part;
    }

    public void setSub_part(String sub_part) {
        this.sub_part = sub_part;
    }

    public String getPacked() {
        return packed;
    }

    public void setPacked(String packed) {
        this.packed = packed;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getNon_unique() {
        return non_unique;
    }

    public void setNon_unique(String non_unique) {
        this.non_unique = non_unique;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String collation) {
        this.collation = collation;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }
}
