package com.yf.coros.common.entity.device;

import java.io.Serializable;
import lombok.Data;

/**
 * 固件条件类,查询或新增固件用到
 *
 * @author Infi
 * @date 17/6/28
 */
@Data
public class FirmwareCriteria implements Serializable {

    private static final long serialVersionUID = -6291029042054860938L;
    private String firmwareType;
    private String firmwareVersion;
    private Integer releaseType;
    private Integer systemType;
    private String appVersion;
    private String deviceId;
    private String uuid;
}
