package com.yf.coros.common.entity.yfheader;

import java.io.Serializable;
import lombok.Data;

/**
 * http请求头部信息-固件信息
 *
 * @author Infi
 * @date 17/4/7
 */
@Data
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = -9205494768455310548L;
    private String algorithmVersion;
    private String mac;
    private String deviceId;
    private String firmwareVersion;
    private String firmwareType;
    private String gpsFirmwareVersion;
    private Long lastSyncTimestamp;
}
