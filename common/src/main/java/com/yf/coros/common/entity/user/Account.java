package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户实体对象
 *
 * @author Infi
 * @date 17/3/22
 */
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 5306163246780107591L;
    private Long userId;
    private String mobile;
    private String email;
    private String facebookId;
    private String weixinId;
    private String weixinName;
    private String facebookName;
    private String pwd;
    private String accessToken;
    private Date validityDate;
    private Integer clientType;
    private Integer loginType;
    private String ipAddress;
    private Date createDate;
    private Date updateDate;
    private Integer registerType;
    private Date registerDate;
    private Integer tmpType;
    private Integer activateStatus;
    private Integer registerClientType;
}
