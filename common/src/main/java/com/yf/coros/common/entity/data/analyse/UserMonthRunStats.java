package com.yf.coros.common.entity.data.analyse;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.yf.coros.common.entity.data.analyse.json.DoubleFormatScaleSerializer;
import lombok.Data;

/**
 * 用户月度统计概要（视图 user_month_run_stats_view）
 * @author lihuaijin
 */
@Data
public class UserMonthRunStats {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;
    private String gmStaminaLevel;
    private String gmVo2max;
    private Long happenMonth;
    private int count;
    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    private double distance;
    private long duration;
    private int step;
    private int calorie;
}
