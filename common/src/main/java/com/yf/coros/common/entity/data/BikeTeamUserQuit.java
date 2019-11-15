package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 团队对应退出信息表
 *
 * @author Infi
 * @date 17/5/12
 */
@Data
public class BikeTeamUserQuit {

    private Long teamId;
    private Integer userId;
    private Long quitTime;
}
