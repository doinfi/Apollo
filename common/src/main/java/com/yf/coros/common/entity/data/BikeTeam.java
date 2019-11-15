package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 自行车导航团队实体类
 *
 * @author Infi
 * @date 17/5/12
 */
@Data
public class BikeTeam {

    private Long teamId;
    private Integer verifyCode;
    private Long admin;
    private String teamName;
    private byte[] routeFile;
    private String routeFileStr;
    private Long creator;
    private Long createTime;
    private Long overTime;
    private Integer status;
    private Long headUser;
    private Long tailUser;
    private Double longitude;
    private Double latitude;
    private Long updateTime;
    private Long gridId;
    private Long teamUpdateTime;
    private Integer isValid;
}
