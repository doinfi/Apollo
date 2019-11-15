package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 心率统计对象对象实体
 *
 * @author Infi
 * @date 17/4/10
 */
@Data
@AllArgsConstructor
public class HeartRateStatisticsData implements Serializable {

    private static final long serialVersionUID = -417480053638599719L;
    private int index;
    private int maxHeartRate;
    private int minHeartRate;
    private int sumHeartRateValue;
    private int countHeartRateNumber;
}
