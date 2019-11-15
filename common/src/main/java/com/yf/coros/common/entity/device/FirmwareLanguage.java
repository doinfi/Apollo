package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;

/**
 * 固件多语言说明
 * @author Infi
 */
@Data
public class FirmwareLanguage implements Serializable {
    private static final long serialVersionUID = -6148130832549297014L;
    private Long firmwareId;
    private String language;
    private String detail;
    private String contentToDevice;
    private Integer isDefault;
}
