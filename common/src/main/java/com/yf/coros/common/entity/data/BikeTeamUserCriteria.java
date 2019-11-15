package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 团队队员列表
 *
 * @author Infi
 * @date 17/5/12
 */
@Data
public class BikeTeamUserCriteria {

    private byte[] sharingData;
    private Long lastUpdateTime;
    private Integer userId;
    private Long teamId;
}
