package com.ecjtu.dynamicdatasource.config.datasource;

/**
 * @author xiexiang
 * @date 2019/10/25 2:54 下午
 */
public enum DBTypeEnum {
    //数据源枚举
    ALI_YUN("aliYun"),
    PAPER("paper");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
