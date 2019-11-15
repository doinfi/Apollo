package com.yf.coros.common.entity.data.googlemap;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组合运动经纬度信息
 *
 * @author Infi
 */
@Data
public class GoogleLatLngElevationSportGroup implements Serializable {
    private static final long serialVersionUID = -7092442468311607973L;
    private int sportIndex;
    private List<GoogleLatLngElevation> latLngElevationList;
}
