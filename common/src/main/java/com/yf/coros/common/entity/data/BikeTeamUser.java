package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 团队队员列表
 *
 * @author Infi
 * @date 17/5/12
 */
@Data
public class BikeTeamUser {

    private Long teamId;
    private Long userId;
    private Long joinTime;
    private byte[] sharingData;
    private Long updateTime;
    private Integer ridingEnd;
    private Boolean exitFlag;
    private String sharingDataStr;
    private Long lastUpdateTime;
    private String nickname;
    private String headPic;
}
