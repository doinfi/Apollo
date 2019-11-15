package com.yf.coros.common.entity.data.analyse;

import lombok.Data;

/**
 * @author lihuaijin
 */
@Data
public class AnalyseRun {

    private Long labelId;
    private Long userId;
    private Integer happenDay;
    private Long startTime;
    private Double altitude;
    private Double altitudeMin;
    private Double altitudeMax;
    private Double temperature;
    private Double temperatureMin;
    private Double temperatureMax;
    private String heartRateDataFilePath;
    private Long createTime;
}
