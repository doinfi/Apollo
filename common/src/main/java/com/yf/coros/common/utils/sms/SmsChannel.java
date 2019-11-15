package com.yf.coros.common.utils.sms;

public interface SmsChannel {

    void sendSMS(String toPhoneNumber,String smsContent);
}
