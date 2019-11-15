package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 概述: 验证码DTO实体<br> 背景: <br>
 *
 * @author Infi
 * @date 17/3/9
 */
@Data
public class CheckCode implements Serializable {

    private static final long serialVersionUID = -8619649336163420301L;
    private Integer id;
    private String account;
    private String checkCode;
    private Date createDate;
    private Integer type;
    private Integer valid;
    private Long userId;
    private String userIdStr;
    private String resetUrl;
}
