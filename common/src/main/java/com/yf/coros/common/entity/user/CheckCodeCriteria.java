package com.yf.coros.common.entity.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 概述: 验证码查询条件<br> 背景: <br>
 *
 * @author Infi
 * @date 17/3/9
 */
@Data
public class CheckCodeCriteria implements Serializable {

    private static final long serialVersionUID = -7862307555472873747L;
    private String mobile;
    private Integer actionType;
    private String checkCode;
    private String appKey;
    private String ipAddress;
    private String account;
    private String email;
    private Integer accountType;
}
