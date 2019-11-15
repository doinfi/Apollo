package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DeviceActivate implements Serializable {


    private static final long serialVersionUID = 2127771194654925073L;

    private Long id;
    private Long userId;
    private String deviceId;
    private String mac;
    private String uuid;
    private String firmwareType;
    private Date activateTime;
    private Date createDate;
    private String ipAddress;
    private Double longitude;
    private Double latitude;
    private Date expiresTime;
    private Integer type;
    private Integer channel;
    private String phase;
    private String updateTime;

    private String idStr;
    private String userIdStr;
    private String deleteAccount;
    private Integer deleteType;
    private Date deleteTime;

    /**
     * 不是表字段，来自于逻辑根据user_id抓取
     */
    private String email;
}
