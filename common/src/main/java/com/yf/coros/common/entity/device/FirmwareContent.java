package com.yf.coros.common.entity.device;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 固件说明
 *
 * @author Infi
 */
@Data
public class FirmwareContent implements Serializable {
    private static final long serialVersionUID = -7247781516457144971L;
    private String content;
    private String contentToDevice;
    private Date date;
    private String version;
    @JSONField(serialize = false)
    private Long firmwareVersionNumber;
    @JSONField(serialize = false)
    private Long firmwareId;
    @JSONField(serialize = false)
    private String language;

}
