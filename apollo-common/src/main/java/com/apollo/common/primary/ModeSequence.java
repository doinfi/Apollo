package com.apollo.common.primary;

/**
 * 模块序号类, 同一秒同模块的序号会累加,系一秒第一个序号是1
 *
 * @author Infi
 * @date 17/5/9
 */
public class ModeSequence {

    private long timestampInSecond;
    private long sequence;


    /**
     * 获取时间戳(秒)
     *
     * @return timestampInSecond 时间戳(秒)
     */
    public long getTimestampInSecond() {
        return timestampInSecond;
    }

    /**
     * 设置时间戳(秒)
     *
     * @param timestampInSecond 时间戳(秒)
     */
    public void setTimestampInSecond(long timestampInSecond) {
        this.timestampInSecond = timestampInSecond;
    }

    /**
     * 获取模块序号
     *
     * @return sequence 模块序号
     */
    public long getSequence() {
        return sequence;
    }

    /**
     * 设置模块序号
     *
     * @param sequence 模块序号
     */
    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
}
