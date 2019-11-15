package com.yf.coros.common.entity.yfheader;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * http请求头部信息
 *
 * @author Infi
 * @date 17/4/7
 */
@Data
public class YFHeader implements Serializable {

    private static final long serialVersionUID = 7690034545181436174L;
    private Long appVersion;
    private Integer clientType;
    private String mobileName;
    private String systemVersion;
    private Integer timezone;
    private Integer releaseType;
    private Long userId;
    private String language;
    private Map<String, DeviceInfo> devices = new HashMap<>();
}
