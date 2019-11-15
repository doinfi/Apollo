package com.yf.coros.common.entity.thirdparty;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateTimeCSTSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author lihuaijin
 */
@Data
public class ThirdpartySportUserTaskVO {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    private Integer failedCount;

    private Integer forceRetryCount;

    private Integer retryingCount;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long lastFailedUpdateTime;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long firstFailedCreateTime;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long lastFailedCreateTime;

    private List<ThirdpartySportTask> failedTaskList;

}
