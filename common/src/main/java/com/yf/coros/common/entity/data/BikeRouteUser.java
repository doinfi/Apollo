package com.yf.coros.common.entity.data;

import java.util.Date;
import lombok.Data;

/**
 * 自行车导航路书用户关系表
 *
 * @author Infi
 * @date 17/5/19
 */
@Data
public class BikeRouteUser {

    private Long routeId;
    private Long userId;
    private Date createTime;
}
