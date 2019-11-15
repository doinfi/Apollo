package com.yf.coros.common.entity.open;

import lombok.Data;

/**
 * 多区域返回值entity
 *
 * @author Infi
 */
@Data
public class AuthCrossRegionVO {
    private String code;
    private String state;

    private String accessToken;
    private String refreshToken;
    private String openId;
    private Integer expiresIn;
}
