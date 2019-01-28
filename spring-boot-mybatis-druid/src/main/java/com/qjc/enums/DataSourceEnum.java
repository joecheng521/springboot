package com.qjc.enums;
/**
 * @author qjc
 * @date 2019年1月28日下午3:55:52
 * @version 1.0.0
 */
public enum DataSourceEnum {
	DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
