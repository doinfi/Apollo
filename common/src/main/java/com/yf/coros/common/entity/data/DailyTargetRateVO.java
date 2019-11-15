package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * 日常数据日历功能(达标率)实体对象
 *
 * @author Infi
 * @date 17/6/1
 */
@Data
public class DailyTargetRateVO implements Serializable {

    private static final long serialVersionUID = 5320114762353347521L;
    private Integer happenDate;
    private Integer calorieStandardRate;
    private Integer motionTimeStandardRate;
}
