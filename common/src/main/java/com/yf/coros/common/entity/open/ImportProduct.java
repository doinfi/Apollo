package com.yf.coros.common.entity.open;


import lombok.Data;

import java.io.Serializable;

/**
 * 运动数据导入支持的产品说明
 *
 * @author dinghui
 * @date 2019/8/29 11:03
 */
@Data
public class ImportProduct implements Serializable {
    Integer source;//产品来源，如1：佳明
    String prodId;//产品ID
    String prodName;//产品名称
}
