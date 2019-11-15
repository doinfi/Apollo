package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试用户
 *
 * @author Infi
 * @date 17/6/28
 */
@Data
public class TestDevice implements Serializable {

    private static final long serialVersionUID = -8353369725330113355L;
    private Long id;
    private String deviceId;
    private String firmwareType;
    private String admin;
    private Date createTime;
    private String idStr;
    private Integer groupId;
    /**
     * 添加类型. 0:手动添加, 1:策略批量生成(随机)
     */
    private Integer addType;
}
