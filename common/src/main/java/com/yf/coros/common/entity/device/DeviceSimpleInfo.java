package com.yf.coros.common.entity.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 设备信息（含设备版本号），from /device/queryFirmware
 * @author lihuaijin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceSimpleInfo {
    private String deviceId;
    private String firmwareType;
    private String firmwareVersion;

    /**
     * 从固件查询参数构造
     * @param firmwareCriteria 固件查询参数
     */
    public DeviceSimpleInfo(FirmwareCriteria firmwareCriteria) {
        this.deviceId = firmwareCriteria.getDeviceId().trim();
        this.firmwareType = firmwareCriteria.getFirmwareType().toUpperCase().trim();
        this.firmwareVersion = firmwareCriteria.getFirmwareVersion().trim();
    }

    public DeviceSimpleInfo(Device device) {
        this.deviceId = device.getDeviceId().trim();
        this.firmwareType = device.getFirmwareType().toUpperCase().trim();
        this.firmwareVersion = device.getFirmwareVersion().trim();
    }
}
