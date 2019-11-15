package com.yf.coros.common.entity.bigdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 活跃度消息族
 * @author lihuaijin
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveMsgFamily {

    private int type;

    private String ip;

    private String appVersion;

    private String result;

    private String module;

    private Long time;

}
