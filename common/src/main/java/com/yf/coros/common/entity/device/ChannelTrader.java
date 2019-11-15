package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class ChannelTrader implements Serializable {

    private Integer traderNo;
    private String traderName;
    private String country;
    private String currency;
    private Integer position;

    /**
     * 非数据库字段，来自于device模块的配置
     */
    private boolean directChannel = false;

}
