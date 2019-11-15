package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lihuaijin
 */
@Data
public class DeviceRule implements Serializable {

    private static final long serialVersionUID = 1623224545721158252L;

    private String deviceId;

    private String firmwareType;

    /**
     * 字段不来自数据库，会在查询后由ruleType解析，写入前组装ruleType
     */
    private Map<String, Integer> ruleMap;

    private Integer ruleType;
    private Integer lastRuleType;
    private String operator;
    private String description;
    private Long createTime;
    private Long updateTime;

    /**
     * 字段不是来自数据库，来自updateTime的转换
     */
    private String updateTimeStr;
    private String ruleTypeStr;
}
