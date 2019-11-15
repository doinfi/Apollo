package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户勋章信息表
 *
 * @author Infi
 * @date 17/6/13
 */
@Data
public class UserMedal implements Serializable {

    private static final long serialVersionUID = -6121388231905324938L;
    private Long userId;
    private Integer medalId;
    private Long timestamp;
    private Integer timezone;
    private Long labelId;
}
