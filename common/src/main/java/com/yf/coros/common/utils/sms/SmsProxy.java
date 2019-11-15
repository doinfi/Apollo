package com.yf.coros.common.utils.sms;

import com.yf.coros.common.utils.sms.channel.KeGaoChannel;
import com.yf.coros.common.utils.sms.channel.TwilioChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送代理 选择不同的短信通道
 * by max.hu
 */
public class SmsProxy {
    /**
     * 海外短信通道
     */
    public final static int CHANNEL_OVERSEAS = 0;
    /**
     * 国内（中国）短信通道
     */
    public final static int CHANNEL_CHINA = 1;

    /**
     * 中国电话号码区号
     */
    public final static String CHINA_PHONE_CODE = "+86";

    static Map<Integer, SmsChannel> channelMap = new HashMap<>();

    static {
        //国内采用科高通道
        channelMap.put(CHANNEL_CHINA, new KeGaoChannel());
        channelMap.put(CHANNEL_OVERSEAS, new TwilioChannel());
    }

    /**
     * 发送短信
     *
     * @param channelType   com.yf.coros.common.utils.sms.SmsProxy#CHANNEL_OVERSEAS 海外通道
     *                      com.yf.coros.common.utils.sms.SmsProxy#CHANNEL_CHINA 国内通道
     * @param toPhoneNumber 接收手机号
     * @param smsContent    短信内容
     */
    public static void sendSms(int channelType, String toPhoneNumber, String smsContent) {
        SmsChannel channel = channelMap.get(channelType);
        channel.sendSMS(toPhoneNumber, smsContent);
    }

    /**
     * 根据手机号码自动判断选择短信通道
     * +86开头的使用国内通道，其他使用海外通道
     *
     * @param toPhoneNumber 接收手机号
     * @param smsContent    短信内容
     */
    public static void sendSms(String toPhoneNumber, String smsContent) {
        int channelType = CHANNEL_OVERSEAS;
        if (toPhoneNumber.contains(CHINA_PHONE_CODE)) {
            channelType = CHANNEL_CHINA;
        }
        sendSms(channelType, toPhoneNumber, smsContent);
    }


    public static void main(String[] args) {
//        SmsProxy.sendSms("+8613710637126", "【COROS】你好，你的好友 infi 在 Coros APP 中添加你为紧急联系人");
        SmsProxy.sendSms("(+86)13710637126", "[COROS] infi2 fell down need your help. Location: https://usercenter.coros.com/coros/view/sos/soscn.html?sosid=411679437610893312");
//        SmsProxy.sendSms("+8618929208672", "【COROS】你好，你的好友 infi2 在 Coros APP 中添加你为紧急联系人");
    }

}
