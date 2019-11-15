package com.yf.coros.common.entity.data.statistics.daily;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 运动数据个数统计
 *
 * @author Infi
 */
@Data
public class SportDataCount implements Serializable {
    private static final long serialVersionUID = 4300791739602142179L;
    @JSONField(serialize = false)
    private Integer happenDay;
    private Integer mode;
    private Integer subMode;
    private Integer count;
    private Double distance;
    private Long duration;
    private Integer totalElevation;
    private Integer totalDecline;
}
