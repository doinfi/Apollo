package com.yf.coros.common.enums;

/**
 * 运动数据推送状态
 *
 * @author Infi
 */
public interface SportDataPushTaskStatusEnum {
    /**
     * 任务初始化
     */
    int TASK_STATUS_INITIAL = 0;

    /**
     * 推送任务进行中
     */
    int TASK_STATUS_RUNNING = 1;

    /**
     * 推送任务失败
     */
    int TASK_STATUS_FAILED = 2;

    /**
     * 推送任务结束
     */
    int TASK_STATUS_FINISHED = 100;
}
