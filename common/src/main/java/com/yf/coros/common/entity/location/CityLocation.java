package com.yf.coros.common.entity.location;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class CityLocation implements Serializable {
    private String name;
    private String country;
    private String province;

    /**
     * 经度
     */
    private String x;

    /**
     * 纬度
     */
    private String y;

}
