package com.yf.coros.common.entity.device;

import java.io.Serializable;
import lombok.Data;

/**
 * 固件版本配置信息
 *
 * @author Infi
 * @date 17/7/28
 */
@Data
public class FirmwareConfig implements Serializable {
    private static final long serialVersionUID = 6991220402242030312L;
    private Long appVersionNumber;
    private String firmwareVersion;
    private Long firmwareVersionNumber;
    private String firmwareType;
    private Integer releaseType;
    private Integer systemType;
    private Long id;
    private String appVersion;
    private String idStr;
    private Integer groupId;
}
