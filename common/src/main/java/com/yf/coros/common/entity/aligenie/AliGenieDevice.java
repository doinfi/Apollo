package com.yf.coros.common.entity.aligenie;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * AliGenie平台设备信息
 *
 * @author Infi
 */
@Data
public class AliGenieDevice implements Serializable {
    private static final long serialVersionUID = 2926903008475416168L;

    private String deviceId;
    private String deviceName;
    private String deviceType;
    private String zone;
    private String brand;
    private String model;
    private String icon;
    private List<AliGenieProperties> properties;
    private List<String> actions;
    private AliGenieExtensions extensions;
}
