package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 日常数据统计对象
 *
 * @author Infi
 */
@Data
public class DailyStatisticData implements Serializable {
    private static final long serialVersionUID = 6137887038082771712L;
    private List<DailyDataCountInDay> dayDataList;
    private List<DailyDataCountInMonth> monthDataList;
}
