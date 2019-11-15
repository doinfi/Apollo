package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户和设备对应关系
 *
 * @author Infi
 */
@Data
public class DeviceUserRecord implements Serializable {
    private static final long serialVersionUID = -5431523206107857653L;
    private Long userId;
    private String firmwareType;
    private String deviceId;
    private Date createTime;
    private Date updateTime;
}
