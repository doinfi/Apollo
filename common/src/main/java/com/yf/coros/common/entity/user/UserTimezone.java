package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户时区变更记录表
 *
 * @author Infi
 * @date 17/6/9
 */
@Data
public class UserTimezone implements Serializable {

    private static final long serialVersionUID = -5273883290870924888L;
    private Long userId;
    private Integer timezone;
    private Date createTime;
}
