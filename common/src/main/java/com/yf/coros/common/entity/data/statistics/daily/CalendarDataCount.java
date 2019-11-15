package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 日历数据
 *
 * @author Infi
 */
@Data
public class CalendarDataCount implements Serializable {
    private static final long serialVersionUID = -604298399460726929L;
    private Integer calorie;
    private Integer motionTime;
    private Integer calorieStandardRate;
    private Integer motionTimeStandardRate;
    private List<SportDataCount> sportCountList;
}
