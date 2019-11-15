package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;

/**
 * 日常数据统计ResponseVO
 * @author Infi
 */
@Data
public class DailyDataCountVO implements Serializable {
    private static final long serialVersionUID = -6917396963441459933L;
    private Integer firstHappenDay;
    private DailyStatisticData statisticData;
}
