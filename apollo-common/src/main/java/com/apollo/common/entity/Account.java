package com.apollo.common.entity;

import java.io.Serializable;

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
    private String name;
}
