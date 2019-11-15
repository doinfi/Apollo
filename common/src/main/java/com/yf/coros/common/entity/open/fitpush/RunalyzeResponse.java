package com.yf.coros.common.entity.open.fitpush;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 第三方runalyze返回结果
 *
 * @author Infi
 */
@Data
public class RunalyzeResponse implements Serializable {
    private static final long serialVersionUID = 7387137602140134L;
    @JSONField(alternateNames = "access_token")
    private String accessToken;
    @JSONField(alternateNames = "token_type")
    private String tokenType;
    @JSONField(alternateNames = "expires_in")
    private Integer expiresIn;
    private String scope;
    private String status;
    private String id;
    @JSONField(alternateNames = "queue_id")
    private Integer queueId;
}
