package com.yf.coros.common.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 固件信息对象
 *
 * @author Infi
 * @date 17/7/28
 */
@Data
@ApiModel("固件")
public class Firmware implements Serializable {
    private static final long serialVersionUID = -2405256778064994819L;
    @ApiModelProperty("固件版本号")
    private Long firmwareId;
    private String firmwareType;
    private String firmwareVersion;
    private Long firmwareVersionNumber;
    private Integer releaseType;
    private Integer systemType;
    private Date addTime;
    private String addUser;
    private String content;
    private String language;
    private Integer forceUpdate;
    private String remarks;
    private String idStr;
    private Long amendmentFirmware;
    private String amendmentVersion;
    private boolean testFirmware;
    private boolean betaFirmware;
    private boolean releaseFirmware;
    private String languageDetail;
    private Integer groupId;
}
