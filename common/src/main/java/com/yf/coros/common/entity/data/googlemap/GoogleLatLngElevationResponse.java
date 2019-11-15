package com.yf.coros.common.entity.data.googlemap;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 根据进纬度获取海拔数据
 * @author Infi
 */
@Data
public class GoogleLatLngElevationResponse implements Serializable {
    private static final long serialVersionUID = -3823791862910883813L;
    private Integer index;
    private List<GoogleLatLngElevation> latLngElevationList;
}
