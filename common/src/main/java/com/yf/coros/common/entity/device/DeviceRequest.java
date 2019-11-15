package com.yf.coros.common.entity.device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Infi
 * @date 17/6/26
 */
@Data
public class DeviceRequest implements Serializable {

    private static final long serialVersionUID = -682246056215948567L;
    private Long userId;
    private Integer clientType;
    private String appVersion;
    private String mobileName;
    private String systemVersion;
    private Integer releaseType;
    private String timezone;
    private Double longitude;
    private Double latitude;
    private List<Device> devices = new ArrayList<>();
}
