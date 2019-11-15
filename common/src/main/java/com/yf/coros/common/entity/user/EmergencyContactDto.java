package com.yf.coros.common.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 紧急联系人实体类
 * Created by hudahua on 2018/5/8.
 */
@Data
public class EmergencyContactDto implements Serializable {

    private static final long serialVersionUID = 7567006153581234407L;
    private List<EmergencyContacts> contactList;
    private Integer sosSwitch;//  SOS 开关 1 开，0 关



}
