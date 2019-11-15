package com.yf.coros.common.label.jni;


import com.yf.lib.w4.sport.W4ParseResult;
import com.yf.lib.w4.sport.W4Timezone;

/**
 * 类描述：解析数据原文件对外业务接口
 *
 * @author pichunhan
 * @ClassName: ILabelJNI
 * @date 2015年12月7日 上午11:32:05
 * @update 2017年10月26日 jni更新coros原始数据解析,主要把卡路里和距离刻度值计算都放在解析库
 */
public interface LabelJNIService {

    /**
     * 方法描述: 解析原始数据文件
     *
     * @param oriDataArr 原始数据byte[]对象
     * @param segmentTimeInMinute 运动时间分段长度(单位:分钟),目前是15分钟
     * @param lastStatus 上一次解析的状态
     * @param registerTimezone 注册时间
     * @return 解析后的标签、分钟、心率数据
     * @throws Exception 异常抛出
     */
    W4ParseResult resolutionSourceData(byte[] oriDataArr, int segmentTimeInMinute,
        byte[] lastStatus,
        W4Timezone registerTimezone) throws Exception;
}
