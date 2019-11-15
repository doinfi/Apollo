package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 自行车导航路书实体类
 *
 * @author Infi
 * @date 17/5/19
 */
@Data
public class BikeRoute implements Serializable {

    private static final long serialVersionUID = -4985210337522210630L;
    private Long routeId;
    private String routeName;
    private Long labelId;
    private byte[] routeFile;
    private String routeMd5;
    private Long creator;
    private String remarks;
    private Date createTime;
    private Integer status;
    private Double distance;
    private Long userId;
}
