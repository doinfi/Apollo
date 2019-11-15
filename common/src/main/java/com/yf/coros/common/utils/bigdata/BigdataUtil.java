package com.yf.coros.common.utils.bigdata;

import com.yf.coros.common.entity.bigdata.ActiveMsg;
import com.yf.coros.common.entity.bigdata.ActiveMsgFamily;
import com.yf.coros.common.entity.yfheader.YFHeader;
import com.yf.coros.common.utils.YfTools;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.security.SecureRandom;
import java.util.TimeZone;

/**
 * 大数据工具类
 * @author lihuaijin
 */
public class BigdataUtil {

    private static final int ROW_KEY_RANDOM_BOUND = Short.MAX_VALUE;
    private static final char SPLIT = '_';
    private static final String ROW_KEY_TIME_PREFIX = "yyyyMMddHHmmssSSS";
    /**
     * 使用东八区时间
     */
    public static final DateTimeFormatter AUTH_TIMESTAMP_FORMATTER =
            DateTimeFormat.forPattern(ROW_KEY_TIME_PREFIX)
            .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+8:00")));

    private static final String UNKNOWN_USER = "unknownUser";

    private static final String ANDROID_NAME = "Android";

    private static final String IOS_NAME = "iOS";

    private static final int IOS_CLIENT_TYPE = 2;

    /**
     * 生成活跃度请求消息的rowkey
     * time_random_userid_app_uri
     * <br/> e.g. 20180421194553811_3212_404077273556205568_Android_login
     * @param responseTime 消息响应时间
     * @param userId 用户id
     * @param yfHeader 请求头域
     * @param restUri 请求uri
     * @return rowkey
     */
    private static String buildActiveMsgRowKey(Long userId, DateTime responseTime, YFHeader yfHeader, String restUri) {
        return responseTime.toString(AUTH_TIMESTAMP_FORMATTER) + SPLIT +
                new SecureRandom().nextInt(ROW_KEY_RANDOM_BOUND) + SPLIT +
                (null == userId ? UNKNOWN_USER : userId.toString()) + SPLIT +
                (null != yfHeader.getClientType() && IOS_CLIENT_TYPE == yfHeader.getClientType() ? IOS_NAME : ANDROID_NAME) + SPLIT + restUri;
    }

    /**
     * 生成活跃度请求实体
     * @param userId 用户id，如果无，传Null
     * @param yfHeader 头域
     * @param productName 产品名
     * @param requestIp 请求IP
     * @param module 模块信息
     * @param result 请求结果
     * @param restUri 请求uri
     * @return 活跃度请求实体
     */
    public static ActiveMsg buildActiveMsg(Long userId, YFHeader yfHeader, String requestIp, String productName, String module, String result, String restUri) {
        ActiveMsg activeMsg = new ActiveMsg();
        activeMsg.setProduct(productName);
        DateTime now = DateTime.now(DateTimeZone.UTC);
        activeMsg.setRowKey(buildActiveMsgRowKey(userId, now, yfHeader, restUri));

        ActiveMsgFamily activeMsgFamily = new ActiveMsgFamily();
        if (null != yfHeader.getClientType() && IOS_CLIENT_TYPE == yfHeader.getClientType()) {
            activeMsgFamily.setAppVersion(IOS_NAME + SPLIT + YfTools.appVersionToString(yfHeader.getAppVersion()));
        }
        else {
            activeMsgFamily.setAppVersion(ANDROID_NAME + SPLIT + YfTools.appVersionToString(yfHeader.getAppVersion()));
        }
        activeMsgFamily.setIp(requestIp);
        activeMsgFamily.setModule(module);
        activeMsgFamily.setResult(result);
        activeMsgFamily.setTime(now.getMillis());
        activeMsgFamily.setType(0);
        activeMsg.setF(activeMsgFamily);
        return activeMsg;

    }
}
