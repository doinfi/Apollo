package com.apollo.common.primary;

import com.apollo.common.entity.enums.MessageKey;
import com.apollo.common.exception.YfException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 主键生成工具 主键共64位: 2位(保留位) + 34位(时间戳) + 7位(机器码) + 7位(模块编号) + 14位(序列号)
 *
 * @author Infi
 * @date 17/5/9
 */
@Slf4j
public class YFPrimaryKeyUtils {
    /**
     * 序列号占14位
     */
    private static final Long SEQUENCE_BITS = 14L;
    /**
     * 模块号占7位
     */
    private static final Long MODE_BITS =  7L;
    /**
     * 机器码或者服务实例编号占7位
     */
    private static final Long WORKER_ID_BITS = 7L;
    /**
     * 机器码或者服务实例编号配置(每台机器或者同一台机器上面的每个实例配置一个编号)
     */
//    private static final Long WORKER_ID = ApplicationContants.PRIMARY_KEY_WORKER_ID;

    /**
     * 模块号位移运算左移索引
     */
    private static final Long modeLeftShift = SEQUENCE_BITS;
    /**
     * 机器码或者服务实例编号位移运算左移索引
     */
    private static final Long workerLeftShift = SEQUENCE_BITS + MODE_BITS;
    /**
     * 时间戳位移运算左移索引
     */
    private static final Long timestampLeftShift = SEQUENCE_BITS + MODE_BITS + WORKER_ID_BITS;

    /**
     * 序列号上限最大值(不能超过该值)
     */
    private static final Long sequenceMask = -1L ^ (-1L << SEQUENCE_BITS);
    /**
     * 初始化变量,下一个时间戳(秒)
     */
    private static Long lastTimestampInSecond = -1L;
    /**
     * 每个模块的序列号,同意秒钟序列号累加,
     */
    private static Map<Integer, ModeSequence> sequenceMap = new ConcurrentHashMap<Integer, ModeSequence>();
    /**
     * 模块编号上限最大值(不能超过该值)
     */
    private long maxModeId = -1L ^ (-1L << MODE_BITS);
    /**
     * 机器码或者服务实例编号上限最大值(不能超过该值)
     */
    private long maxWorkerId = -1L ^ (-1L << WORKER_ID_BITS);

    /**
     * 获得分布式主键
     *
     * @param modeId   模块ID
     * @param workerId 机器码或者服务实例编号配置(每台机器或者同一台机器上面的每个实例配置一个编号)
     * @return 主键
     * @throws YfException 异常抛出
     */
    public static synchronized long getId(Integer modeId, Long workerId) throws YfException {
        long timestampInSecond = timeGen();
        if (timestampInSecond < lastTimestampInSecond) {
            throw new YfException(MessageKey.SYSTEM_ERROR);
        }
        ModeSequence modeSequence = sequenceMap.get(modeId);
        if (modeSequence == null) {
            modeSequence = new ModeSequence();
            modeSequence.setSequence(0);
            modeSequence.setTimestampInSecond(timestampInSecond);
            sequenceMap.put(modeId, modeSequence);
        } else if (lastTimestampInSecond == modeSequence.getTimestampInSecond()) {
            if ((modeSequence.getSequence() + 1) > sequenceMask) {
                timestampInSecond = tilNextMillis(lastTimestampInSecond);
                modeSequence.setTimestampInSecond(timestampInSecond);
                modeSequence.setSequence(0);
            } else {
                modeSequence.setSequence(modeSequence.getSequence() + 1);
            }
        } else {
            modeSequence.setTimestampInSecond(timestampInSecond);
            modeSequence.setSequence(0);
        }
        //TODO 测试使用 start
//        log.info(String.valueOf(timestampInSecond) + ":" + String.valueOf(modeSequence.getSequence()) + ":" + String.valueOf(modeId)
//                + ":" + String.valueOf((modeSequence.getTimestampInSecond() << timestampLeftShift) |
//                (WORKER_ID << workerLeftShift) |
//                (modeId << modeLeftShift) |
//                modeSequence.getSequence()));
//        TODO 测试使用 end

        lastTimestampInSecond = timestampInSecond;
        return (modeSequence.getTimestampInSecond() << timestampLeftShift) |
            (workerId << workerLeftShift) |
            (modeId << modeLeftShift) |
            modeSequence.getSequence();
    }

    /**
     * 等待下一秒时间戳(秒)
     *
     * @param lastTimestamp 时间戳(秒)
     * @return 返回下一秒的时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while ((timestamp <= lastTimestamp)) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取系统当前时间戳(秒)
     *
     * @return 返回时间戳(秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis() / 1000;
    }
}
