package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 *
 * @author Infi
 */
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = -8193009038462604309L;

    private Long id;
    private String account;
    private String pwd;
    private String name;
    private String role;
    private String note;
    private String roleName;
    private Date createDate;
    private String accessToken;
    private Date expireDate;
    private Integer moduleRole;
    private Integer actionRole;
}
