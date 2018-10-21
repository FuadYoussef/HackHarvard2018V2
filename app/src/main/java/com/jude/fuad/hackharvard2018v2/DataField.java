package com.jude.fuad.hackharvard2018v2;

public class DataField {
    private String dataType;
    private String dataVal;

    public DataField(String dataType, String dataVal) {
        this.dataType = dataType;
        this.dataVal = dataVal;
    }
    public String getDataType() {
        return dataType;
    }
    public String getDataVal() {
        return dataVal;
    }
    @Override
    public  String toString() {
        return dataType+"~"+dataVal;
    }
}
