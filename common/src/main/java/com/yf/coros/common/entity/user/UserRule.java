package com.yf.coros.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lihuaijin
 */
@Data
public class UserRule implements Serializable {

    private static final long serialVersionUID = 1672125544815524362L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    /**
     * 字段不是来自数据库，来自用户关联信息
     */
    private String email;

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
