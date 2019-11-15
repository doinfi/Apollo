/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.utils.twilio;

import com.twilio.sdk.LookupsClient;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.lookups.PhoneNumber;
import com.yf.coros.common.config.ApplicationContants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * twilio 短信发送工具类
 *
 * @author Infi
 */
public class TwilioYFUtils {

    private static TwilioRestClient client;

    /**
     * 初始化twilio连接
     *
     * @return TwilioRestClient
     */
    private static TwilioRestClient geTwilioRestClient() {
        if (client == null) {
            client = new TwilioRestClient(ApplicationContants.TWILIO_ACCOUNT_SID,
                    ApplicationContants.TWILIO_AUTH_TOKEN);
        }
        return client;
    }

    public static void sendSMS(String toPhoneNumber, String content) throws TwilioRestException {
        sendSMS(toPhoneNumber, ApplicationContants.TWILIO_FROM_NUMBER, content);
    }

    /**
     * twilio 发送短信
     *
     * @param toNumber   收件人电话号码
     * @param fromNumber twilio 账户电话号码
     * @param body       短信内容
     * @throws TwilioRestException 排除twilio短信发送异常
     */
    public static void sendSMS(String toNumber, String fromNumber, String body)
            throws TwilioRestException {
        TwilioRestClient twilioRestClient = geTwilioRestClient();
        final Account mainAccount = twilioRestClient.getAccount();

        // 发短信
        final SmsFactory messageFactory = mainAccount.getSmsFactory();
        final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
        messageParams
                .add(new BasicNameValuePair("To", toNumber));
        messageParams.add(new BasicNameValuePair("From",
                fromNumber));
        messageParams.add(new BasicNameValuePair("Body", body));
        messageFactory.create(messageParams);
    }

    /**
     * lookups检查电话号码的正确性
     *
     * @param number 电话号码
     * @return 电话号码的信息
     */
    public static PhoneNumber lookupsCheckPhoneNumber(String number) {
        LookupsClient lookupsClient = new LookupsClient(ApplicationContants.TWILIO_ACCOUNT_SID,
                ApplicationContants.TWILIO_AUTH_TOKEN);
        try {
            PhoneNumber phoneNumber = lookupsClient.getPhoneNumber(number, true);
            phoneNumber.getType();
            return phoneNumber;
        } catch (Exception e) {
            return null;
        }
    }
}
