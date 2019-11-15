package com.yf.coros.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author yangyueming
 */
@Slf4j
public class SmsProxyUtils {

    private static final String SMS_URL = "http://sdk.entinfo.cn:8060/z_mdsmssend.aspx";
    private static final String SMS_SDK = "SDK-GKG-010-00777";
    private static final String SMS_CHANNEL = "2-88[3-[578";

    /**
     * 发送验证码短信<br>
     *
     * @param mobile 电话号码<br>
     * @param content 短信内容<br>
     */
    public static void sendVerifyCode(String mobile, String content) {
        String returnMsg = sendSMS(mobile, content);
        log.info("send verify code==" + returnMsg);
    }

    /**
     * 调用短信接口，发送短信，正式的接口 2014-01-14
     */
    public static String sendSMS(String mobile, String content) {
        String returnMsg = "";
        try {
            // 创建StringBuffer对象用来操作字符串
            StringBuffer sb = new StringBuffer();
            sb.append(SMS_URL + "?sn=" + SMS_SDK);
            String pwdMd5 = YfMd5.getMD5(SMS_SDK + SMS_CHANNEL).toUpperCase();
            sb.append("&pwd=" + pwdMd5);
            // 向StringBuffer追加手机号码
            sb.append("&mobile=" + mobile);
            // 向StringBuffer追加消息内容转URL标准码
            sb.append("&content=" + URLEncoder.encode(content, "gb2312"));
            sb.append("&stime=&rrid=&ext=");
            URL url = new URL(sb.toString());
            // 打开url连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置url请求方式 ‘get’ 或者 ‘post’
            connection.setRequestMethod("POST");
            // 返回发送结果
            returnMsg = IOUtils.toString(new InputStreamReader(url.openStream(), "UTF-8"));
        } catch (Exception e) {
            log.error("SMS send error==" + e.getMessage());
        }
        return returnMsg;
    }
}
