package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class GomoreDeviceKey implements Serializable {

    private String gmDevice;
    private String gmDeviceSecret;
    private String deviceId;

    @JSONField(serialize = false)
    private Integer bindType;

    @JSONField(serialize = false)
    private Integer syncStatus;

    @JSONField(serialize = false)
    private Long updateTime;

    @JSONField(serialize = false)
    private String sdkSn;

    public GomoreDeviceKeyForSync cast4Sync() {
        GomoreDeviceKeyForSync gomoreDeviceKeyForSync = new GomoreDeviceKeyForSync();
        BeanUtils.copyProperties(this, gomoreDeviceKeyForSync);
        return gomoreDeviceKeyForSync;
    }
}
