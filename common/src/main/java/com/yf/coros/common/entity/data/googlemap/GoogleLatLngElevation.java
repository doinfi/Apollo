package com.yf.coros.common.entity.data.googlemap;

import lombok.Data;

import java.io.Serializable;

/**
 * 根据进纬度获取海拔数据
 * @author Infi
 */
@Data
public class GoogleLatLngElevation implements Serializable {
    private static final long serialVersionUID = 3522853543758397325L;
    private Double elevation;
    private Double lat;
    private Double lng;
    private Long timestamp;
}
