package com.yf.coros.common.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息历史表，保存用户修改最大心率和静息心率的历史记录
 *
 * @author Infi
 */
@Data
public class UserInfoHistory implements Serializable {
    private static final long serialVersionUID = 8856069264249270623L;
    private Long userId;
    private Integer type;
    private Integer value;
    private Date createTime;
    private Date updateTime;
}
