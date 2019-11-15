package com.yf.coros.common.enums;

/**
 * 运动数据状态
 *
 * @author Infi
 */
public interface LabelState {
    /**
     * 可用的
     */
    int USABLE = 1;
    /**
     * 删除的
     */
    int DELETED = 0;
    /**
     * 错误的运动数据
     */
    int ERROR = 2;
}
