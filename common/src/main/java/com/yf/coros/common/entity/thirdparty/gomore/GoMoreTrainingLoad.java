package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreTrainingLoad implements Serializable {

    private static final long serialVersionUID = 3782122911551021765L;

    @JSONField(deserialize = false)
    private Long userId;

    @JSONField(deserialize = false)
    private Integer happenDay;

    private String workLoadStamina;

    private String workLoadAerobic;

    private String workLoadAnaerobic;

    private String legacyLoadStamina;

    private String legacyLoadAerobic;

    private String legacyLoadAnaerobic;

    private String time;

    @JSONField(deserialize = false)
    private Long updateTime;

    private Float trainingLoad;
}
