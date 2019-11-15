package com.yf.coros.common.entity.device.appversion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * APP版本信息
 *
 * @author Infi
 */
@Data
public class AppVersion implements Serializable {
    private static final long serialVersionUID = 2946954067278032334L;
    private Long id;
    private Long appVersion;
    private String appVersionString;
    private Integer clientType;
    private Integer releaseType;
    private String remarks;
    private String creator;
    private String detail;
    private Date createTime;
    private String language;
    @JSONField(name = "thirdUrl")
    private String firUrl;
    private String updatePageUrl;
    private Integer appType;
    private String apkUrl;
    private String apkMd5;
}
