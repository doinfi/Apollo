package com.yf.coros.common.utils.page;

/**
 * 数据库排序
 *
 * @author dinghui
 * @date 2019/7/4 17:27
 */
public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}