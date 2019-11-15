package com.yf.coros.common.utils.sms.channel;

import com.twilio.sdk.TwilioRestException;
import com.yf.coros.common.utils.sms.SmsChannel;
import com.yf.coros.common.utils.twilio.TwilioYFUtils;

public class TwilioChannel implements SmsChannel {

    @Override
    public void sendSMS(String toPhoneNumber, String smsContent) {
        try {
            TwilioYFUtils.sendSMS(toPhoneNumber,smsContent);
        } catch (TwilioRestException e) {
            e.printStackTrace();
        }
    }

}
