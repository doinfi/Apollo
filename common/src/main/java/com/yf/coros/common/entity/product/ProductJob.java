package com.yf.coros.common.entity.product;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品执行操作任务
 *
 * @author Infi
 */
@Data
public class ProductJob implements Serializable {
    private static final long serialVersionUID = 7587359702848181702L;
    private Integer type;
    private Date lastTime;
    private Date createTime;
    private Date updateTime;
}
