package com.yf.coros.common.enums;

/**
 * 运动数据推送状态
 *
 * @author Infi
 */
public interface FitPushTaskStatusEnum {
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
     * token无效导致推送失败
     */
    int TOKEN_INVALID = 3;

    /**
     * 推送任务结束
     */
    int TASK_STATUS_FINISHED = 100;
    /**
     * fit文件不存在，不需要上传
     */
    int FIT_FILE_NOT_EXISTS = 101;
}
