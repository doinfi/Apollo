package com.yf.coros.common.entity.device;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 手环绑定、激活、使用信息实体类
 *
 * @author Infi
 * @date 17/6/26
 */
@Data
public class Device implements Serializable {

    private static final long serialVersionUID = -7196720387626143564L;
    private Long id;
    private Long userId;
    private Integer clientType;
    private String appVersion;
    private String mobileName;
    private String systemVersion;
    private Integer releaseType;
    private String deviceId;
    private String algorithmVersion;
    private String firmwareVersion;
    private String firmwareType;
    private String gpsFirmwareVersion;
    private String gpsChipVersion;
    private Long lastSyncTimestamp;
    private String timezone;
    private Date activateTime;
    private Date createDate;
    private Date updateDate;
    private String uuid;
    private String ipAddress;
    private Double longitude;
    private Double latitude;
    private Date expiresTime;
    private Integer type;
    /**
     * 渠道
     */
    private Integer channel;
    /**
     * 批次，产品阶段：PVT、DVT、MP
     */
    private String phase;
    private String mac;
}
