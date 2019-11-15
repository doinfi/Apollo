package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 自行车导航路书实体类
 *
 * @author Infi
 * @date 17/5/19
 */
@Data
public class BikeRouteCriteria {

    private Long routeId;
    private String routeName;
    private String remarks;
    private Integer pageIndex;
    private Integer userId;
    private Long labelId;
}
