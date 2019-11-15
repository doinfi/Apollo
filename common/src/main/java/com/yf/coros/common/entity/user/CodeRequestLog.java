package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 概述: 验证码请求日志DTO对象<br> 背景: <br>
 *
 * @author Infi
 * @date 17/3/9
 */
@Data
public class CodeRequestLog implements Serializable {

    private static final long serialVersionUID = 9208468857995128573L;
    private Long uuid;
    private String remoteIpAddress;
    private Long remoteIpNum;
    private Date createDate;
    private Integer days;
    private String account;
    private Integer tag;
}
