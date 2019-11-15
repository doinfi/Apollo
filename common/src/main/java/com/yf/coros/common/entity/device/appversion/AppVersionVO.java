package com.yf.coros.common.entity.device.appversion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * APP版本信息
 *
 * @author Infi
 */
@Data
public class AppVersionVO implements Serializable {
    private static final long serialVersionUID = -3433468738825137104L;
    private Long id;
    private Long appVersion;
    private String appVersionString;
    private Integer clientType;
    private Integer releaseType;
    private String detail;
    private String idString;
    private String defaultLanguage;
    private String zhDetail;
    private String enDetail;
    private String deDetail;
    private String frDetail;
    private String itDetail;
    private String esDetail;
    private String idDetail;
    private String jpDetail;
    private Date createTime;
    private List<AppVersionDetail> appVersionDetailList;
    @JSONField(name = "thirdUrl")
    private String firUrl;
    private Integer appType;
    private String apkUrl;
    private String apkMd5;
    private String apkMd5Code;
}
