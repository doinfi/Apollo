package com.yf.coros.common.enums;

/**
 * 轨迹任务状态
 * 状态：0、初始状态，1、任务执行中，2、任务失败，100、完成，102、重试两次失败就不再重试
 *
 * @author Infi
 */
public interface ElevationTaskStatus {
    /**
     * 开始
     */
    int TASK_STATUS_INITIAL = 0;

    /**
     * 执行中
     */
    int TASK_STATUS_RUNNING = 1;

    /**
     * 执行失败
     */
    int TASK_STATUS_FAILED = 2;

    /**
     * 执行完成
     */
    int TASK_STATUS_FINISHED = 100;

    /**
     * 保留状态，当请求google失败两次，不再重试
     */
    int TASK_STATUS_FAILED_DONOT_TRY_AGAIN = 102;
}
