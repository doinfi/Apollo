package com.yf.coros.common.entity.data;

import lombok.Data;

/**
 * 用户数据缓存对象
 *
 * @author Infi
 * @date 17/5/17
 */
@Data
public class BikeUserDataCache {

    private Integer userId;
    private byte[] sharingData;
    private boolean isExit;
    private long lastUpdateTime;
}
