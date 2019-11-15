package com.yf.coros.common.entity.device;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * 激活统计实体
 * @author lihuaijin
 */
@Data
public class DeviceActivateCountStatInfo implements Serializable {

    private static final long serialVersionUID = 2050714125273218703L;

    private String happenDay;

    private String firmwareType;

    private Integer channel;

    private int count;

    /**
     * 该值不是来自于查询，而是来自于统计整合
     */
    private String traderName;

    /**
     * 该值不是来自于查询，而是来自于统计整合
     */
    private String country;

    /**
     * 该值不是来自于查询，而是来自于统计整合
     */
    private String currency;

    /**
     * 该值不是来自于查询，而是来自于统计整合
     */
    private DateTime happenDate;

    public boolean isSameStat(DeviceActivateCountStatInfo other) {
        boolean channelEqual = null == this.channel ? null == other.channel : this.channel.equals(other.channel);
        return channelEqual
                && StringUtils.equals(this.happenDay, other.happenDay)
                && StringUtils.equals(this.firmwareType, other.firmwareType);
    }
}
