package com.yf.coros.common.entity.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class DeviceLogFile implements Serializable {

    private static final long serialVersionUID = -2614676885202133871L;

    private Long id;
    private String deviceId;
    private Long timestamp;
    private String timestampStr;
    private Integer happenDay;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;
    private String email;

    private String mobileVersion;
    private String mobileName;
    private String appVersion;
    private Integer logType;

    private String fileName;
    private String firmwareVersion;
    private String firmwareType;
    private String algorithmVersion;

    @JSONField(serialize = false)
    private byte[] logFileData;

    private Integer logFileLength;

    private Long createTime;
}
