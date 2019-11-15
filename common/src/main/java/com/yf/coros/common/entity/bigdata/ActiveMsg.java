package com.yf.coros.common.entity.bigdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 活跃度请求消息实体
 * @author lihuaijin
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveMsg {

    private String product;

    private String rowKey;

    private ActiveMsgFamily f;
}
