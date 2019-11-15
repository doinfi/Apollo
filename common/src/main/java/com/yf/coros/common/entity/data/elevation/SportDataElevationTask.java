package com.yf.coros.common.entity.data.elevation;

import com.yf.coros.common.entity.data.googlemap.GoogleLatLngElevation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 海拔校准任务对象
 *
 * @author Infi
 */
@Data
public class SportDataElevationTask implements Serializable {
    private static final long serialVersionUID = 1131139818657076756L;
    private Long taskId;
    private Long userId;
    private Long labelId;
    private Integer mode;
    private Integer status;
    private String errorCode;
    private Long createTime;
    private Long updateTime;
    private List<GoogleLatLngElevation> latLngElevationList;
}
