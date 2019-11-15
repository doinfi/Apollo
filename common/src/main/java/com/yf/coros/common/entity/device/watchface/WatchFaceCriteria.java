package com.yf.coros.common.entity.device.watchface;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表盘查询对象
 *
 * @author Infi
 */
@Data
public class WatchFaceCriteria implements Serializable {
    private static final long serialVersionUID = -3517701411144553609L;
    private String firmwareType;
    private Integer releaseType;
    private Integer watchFaceType;
    private String language;
    private Integer queryType;
    private Long watchFaceId;
    private Integer size;
    private List<Integer> watchFaceIdList;
}
