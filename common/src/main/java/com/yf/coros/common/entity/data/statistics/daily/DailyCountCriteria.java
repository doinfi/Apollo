package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 日常统计数据查询条件
 *
 * @author Infi
 */
@Data
public class DailyCountCriteria implements Serializable {
    private static final long serialVersionUID = -6345106990094540670L;
    private Integer statisticType;
    private List<Integer> dataType;
    private Integer startTime;
    private Integer endTime;
}

