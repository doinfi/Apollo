package com.yf.coros.common.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 紧急联系人实体类
 * Created by hudahua on 2018/5/8.
 */
@Data
public class EmergencyContacts implements Serializable {

    private static final long serialVersionUID = 3559525758356907465L;
    private Long userId;
    private Integer id;//此ID为紧急联系人顺序 1-3
    private String contactsMobile;
    private String phoneCode;
    private String contactNote;
    private String contactCountryCode;
    private Integer status;


}
