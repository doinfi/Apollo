package com.yf.coros.common.entity.bigdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * kafka消息实体
 * @author lihuaijin
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaMessage {
    private String message;

    private Long time;
}
