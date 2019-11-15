package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 自行车导航团队条件实体类
 *
 * @author Infi
 * @date 17/5/12
 */
@Data
public class BikeTeamCriteria {

    private String verifyCode;
    private Double longitude;
    private Double latitude;
    private Long userId;
}
