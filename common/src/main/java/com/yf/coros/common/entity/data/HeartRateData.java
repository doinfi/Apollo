package com.yf.coros.common.entity.data;

import java.io.Serializable;

import lombok.Data;

/**
 * 心率统计对象对象实体
 *
 * @author Infi
 * @date 17/4/10
 */
@Data
public class HeartRateData implements Serializable {

    private static final long serialVersionUID = 8899024009496146985L;
    private long timestampInSecond;
    private short rate;
    /**
     * (weloop)0 自动检测，1手动检测; (coros)非0表示是睡眠心率
     * 0-起床(日常心率)，1-清醒，2-安静，3-浅睡，4-深睡
     */
    private byte mode;
    /**
     * 心率时区，以15分钟为单位。 东八区（utc+8） = 8*60/15 = 32 西八区（utc-8） = =8*60/15 = -32
     */
    private byte timezoneIn15Minutes;
    /**
     * 能量值
     */
    private byte energy;
}
