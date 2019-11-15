package com.yf.coros.common.entity.data.statistics.daily;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 标记新增的需要统计的运动数据
 *
 * @author Infi
 */
@Data
public class SportDataModeStatistics implements Serializable {
    private static final long serialVersionUID = -1248128633517265415L;
    private Integer happenDay;
    private Integer happenMonth;
    private Integer mode;
    private Integer subMode;
    private String happenDayModeKey;
    private String happenMonthModeKey;
}
