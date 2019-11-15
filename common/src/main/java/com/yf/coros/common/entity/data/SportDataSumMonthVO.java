package com.yf.coros.common.entity.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 月统计概要
 * by max.hu
 * Created by hudahua on 2018/5/17.
 */
@Data
public class SportDataSumMonthVO implements Serializable {
    private static final long serialVersionUID = 658374351509137936L;
    private Integer month;
    /**
     * 当月运动次数
     */
    private Integer count;
    private Long duration;
    private Long time = 0L;
    private Float distance;
    private Integer calories;
    private Integer totalElevation;
    private Integer totalDecline;
}
