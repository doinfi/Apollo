package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateDeserializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.NewUserDeserializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreLoginResponse implements Serializable {

    private static final long serialVersionUID = 1731085297269249229L;

    @JSONField(alternateNames = "new_user", deserializeUsing = NewUserDeserializer.class)
    private Boolean newUser;

    @JSONField(alternateNames = "user_id")
    private String gmUserId;

    @JSONField(alternateNames = "expiration_date", deserializeUsing = DateDeserializer.class)
    private Long expireTime;

    @JSONField(alternateNames = "token")
    private String token;
}
