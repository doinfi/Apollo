package com.yf.coros.common.enums;

/**
 * 日常数据类型<br> ADD 需要新增的数据<br> MODIFY 需要修改的数据<br> NO_CHANGE 不需要修改不需要新增的数据<br> <p>
 *
 * @author Infi
 * @date 17/2/19
 */
public interface DailyDataStatusEnum {

    /**
     * 需要新增的数据
     */
    int ADD = 1;
    /**
     * 需要修改的数据
     */
    int MODIFY = 2;
    /**
     * 不需要修改不需要新增的数据
     */
    int NO_CHANGE = 3;
}
