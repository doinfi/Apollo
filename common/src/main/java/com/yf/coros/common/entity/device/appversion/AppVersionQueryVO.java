package com.yf.coros.common.entity.device.appversion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * APP版本信息
 *
 * @author Infi
 */
@Data
public class AppVersionQueryVO implements Serializable {
    private static final long serialVersionUID = -3228095184816287581L;
    private Long appVersion;
    private String appVersionString;
    private String detail;
    private String language;
    @JSONField(name = "thirdUrl")
    private String firUrl;
    @JSONField(name = "firUrl")
    private String firUrlForAndroid;
    private String updatePageUrl;
    private AppFile appFile;
}
