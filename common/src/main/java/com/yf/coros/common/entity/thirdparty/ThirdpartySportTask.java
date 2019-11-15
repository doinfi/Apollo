package com.yf.coros.common.entity.thirdparty;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateTimeCSTSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class ThirdpartySportTask implements Serializable {

    private static final long serialVersionUID = 2308113729692332306L;
    private Long taskId;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long labelId;
    private Integer labelIndex;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long startTime;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long endTime;
    private String sportId;
    private Integer status;
    private Integer subStatus;
    private Integer timezone;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long createTime;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long updateTime;
    private Integer forceRetry;
}
