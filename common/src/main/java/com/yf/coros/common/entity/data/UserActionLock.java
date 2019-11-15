package com.yf.coros.common.entity.data;

import lombok.Data;

import java.util.Date;

/**
 * 用户操作锁
 *
 * @author Infi
 */
@Data
public class UserActionLock {
    private Long userId;
    private Integer type;
    private Integer status;
    private Date updateTime;
}
