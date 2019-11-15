package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备信息实体类
 *
 * @author dinghui
 * @date 2019/9/26 16:33
 */
@Data
public class DeviceData implements Serializable {
    /**
     * 设备名称
     */
    String deviceName;
    /**
     * 设备类型
     */
    Integer deviceType;
}
