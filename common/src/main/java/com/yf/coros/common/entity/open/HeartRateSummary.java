package com.yf.coros.common.entity.open;


import lombok.Data;

/**
 * 心率概要数据
 * @author Infi
 */
@Data
public class HeartRateSummary {
    private Integer max;
    private Integer min;
    private Integer avg;
}
