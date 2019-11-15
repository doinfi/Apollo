package com.yf.coros.common.utils.sms.channel;

import com.alibaba.fastjson.JSON;
import com.yf.coros.common.utils.YfMd5;
import com.yf.coros.common.utils.sms.SmsChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 国内科高短信通道商
 */
@Slf4j
public class KeGaoChannel implements SmsChannel {
    private static final String SMS_URL = "http://sdk.entinfo.cn:8060/z_mdsmssend.aspx";
    private static final String SMS_SDK = "SDK-GKG-010-00777";
    private static final String SMS_CHANNEL = "2-88[3-[578";
    /**
     * 中国电话号码区号
     */
    public final static String CHINA_PHONE_CODE = "+86";
    /**
     * 中国电话号码区号
     */
    public final static String CHINA_PHONE_CODE_AND_BRACKET = "(+86)";

    @Override
    public void sendSMS(String mobile, String content) {

        try {
            // 国内短信号码去掉区号
            mobile = StringUtils.trim(mobile);
            if (StringUtils.startsWith(mobile, CHINA_PHONE_CODE)) {
                mobile = StringUtils.substringAfter(mobile, CHINA_PHONE_CODE);
            }
            if (StringUtils.startsWith(mobile, CHINA_PHONE_CODE_AND_BRACKET)) {
                mobile = StringUtils.substringAfter(mobile, CHINA_PHONE_CODE_AND_BRACKET);

            }
            mobile = StringUtils.trim(mobile);

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
            List<String> returnMsg = IOUtils.readLines(url.openStream(), "UTF-8");
            log.debug("短信发送 电话号码：" + mobile + "发送状态：" + JSON.toJSONString(returnMsg));
        } catch (Exception e) {
            log.error("短信发送失败 电话号码：" + mobile, e);
        }
    }
}
