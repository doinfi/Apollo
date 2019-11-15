package com.yf.coros.common.entity.open.fitpush;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方客户信息
 * @author Infi
 */
@Data
public class FitPushClient implements Serializable {
    private static final long serialVersionUID = 2967917306551609454L;
    private Integer openType;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String company;
    private String contactPhone;
    private Date createTime;
    private String pushActionUrl;
    private String pushHealthUrl;
    private String refreshTokenUrl;
}
