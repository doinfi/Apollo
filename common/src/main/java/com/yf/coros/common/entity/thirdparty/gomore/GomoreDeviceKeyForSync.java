package com.yf.coros.common.entity.thirdparty.gomore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 用于同步的GomoreDeviceKey实体，
 *
 * @author lihuaijin
 */
@Data
public class GomoreDeviceKeyForSync implements Serializable {

    private String gmDevice;

    private String gmDeviceSecret;

    private String deviceId;

    private Integer bindType;

    private Integer syncStatus;

    private Long updateTime;

    private String sdkSn;
}
