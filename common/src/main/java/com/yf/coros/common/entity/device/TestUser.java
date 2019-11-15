package com.yf.coros.common.entity.device;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 测试用户
 *
 * @author Infi
 * @date 17/6/28
 */
@Data
public class TestUser implements Serializable {

    private static final long serialVersionUID = -8353369725330113355L;
    private Long id;
    private Long userId;
    private String admin;
    private Date createTime;
    private String idStr;
    private String userIdStr;
    private Integer groupId;
}
