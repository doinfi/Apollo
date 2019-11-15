package com.yf.coros.common.entity.crossregion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户在另一区域已准备注册或绑定，本区域保留该用户，一定时间内禁止注册
 * @author lihuaijin
 */
@Data
@NoArgsConstructor
public class UserReserve implements Serializable {

    private static final long serialVersionUID = -1821885413447354021L;

    private Long id;

    private String account;

    private int accountType;

    private Date reserveTime;

}
