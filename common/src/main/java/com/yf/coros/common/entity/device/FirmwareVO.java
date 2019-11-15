package com.yf.coros.common.entity.device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 固件信息返回类
 *
 * @author Infi
 * @date 17/6/29
 */
@Data
public class FirmwareVO implements Serializable {
    private static final long serialVersionUID = -8434715684060556168L;
    private List<FirmwareFile> files = new ArrayList<>();
    private String firmwareType;
    private String content;
    private String contentToDevice;
    private Date date;
    private String version;
    private Integer forceUpdate;
    private String appTargetVersion;
    private List<FirmwareContent> latestFirmwareList;
}
