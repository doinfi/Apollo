package com.yf.coros.common.enums;

/**
 * 轨迹数据状态
 *
 * @author Infi
 * @date 18/3/2
 */
public interface TrackState {

    /**
     * 正常状态，可用的数据
     */
    int USABLE = 1;
    /**
     * 重复提交的轨迹数据
     */
    int REPEAT = 0;
}
