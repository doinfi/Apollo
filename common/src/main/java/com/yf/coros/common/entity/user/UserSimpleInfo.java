package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户主要信息,其他模块提供用户主要信息
 *
 * @author Infi
 * @date 17/3/22
 */
@Data
public class UserSimpleInfo implements Serializable {

    private static final long serialVersionUID = 3977046199244219022L;
    private Long userId;
    private Integer sex;
    private Float stature;
    private Float weight;
    private Integer birthday;
    private Date validityDate;
}
