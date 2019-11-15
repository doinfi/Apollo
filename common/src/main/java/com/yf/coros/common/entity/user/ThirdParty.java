package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 第三方帐号信息
 *
 * @author Infi
 */
@Data
public class ThirdParty implements Serializable {
    private static final long serialVersionUID = 4851014819284723772L;
    private Long userId;
    private String thirdPartyToken;
    private Integer thirdPartyId;
    private Date createTime;
    private String refreshToken;
    private String userExt;
    private Integer isValid;
    private Date expireTime;
    private String tokenType;
    private Integer expiresInSeconds;
}
