package com.yf.coros.common.entity.open.fitpush;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 第三方relive返回结果
 *
 * @author Infi
 */
@Data
public class ReliveResponse implements Serializable {
    private static final long serialVersionUID = -8636502246185696035L;
    @JSONField(alternateNames = "access_token")
    private String accessToken;
    @JSONField(alternateNames = "token_type")
    private String tokenType;
    @JSONField(alternateNames = "expires_in")
    private Integer expiresIn;
    private String scope;
    private String status;
    private String id;
}
