package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;

/**
 * 心率统计数据
 *
 * @author Infi
 */
@Data
public class HeartRateDataCount implements Serializable {
    private static final long serialVersionUID = 6264128589237708833L;
    private Integer maxHeartRate;
    private Integer minHeartRate;
    private Integer avgHeartRate;
}
