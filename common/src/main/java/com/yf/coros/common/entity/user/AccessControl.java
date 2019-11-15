package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 概述: 判断验证码dto<br> 背景:<br>
 *
 * @author Infi
 * @date 17/3/9
 */
@Data
public class AccessControl implements Serializable {

    private static final long serialVersionUID = -1945867960286889808L;

    private Integer id;
    private String account;
    private String checkCode;
    private Integer actionType;
    private String ip;
    private Date createDate;
}
