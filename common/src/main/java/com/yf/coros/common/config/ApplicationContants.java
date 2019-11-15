package com.yf.coros.common.config;

/**
 * @author Infi
 * @date 17/11/9
 */
public interface ApplicationContants {

    Integer TOKEN_LENGTH = 32;
    Integer BUFFER_SIZE = 1024;
    Integer BIKE_TIME_SEARCH_RADIUS = 300;
    Long PRIMARY_KEY_SEQUENCE_BITS = 14L;
    Long PRIMARY_KEY_MODE_BITS = 7L;
    Long PRIMARY_KEY_WORKER_BITS = 7L;
    Long PRIMARY_KEY_WORKER_ID = 1L;
    String TWILIO_ACCOUNT_SID = "AC5815eea04232c559b7fc6b1edeb1b3ac";
    String TWILIO_AUTH_TOKEN = "a4f25f549687a341e849d2e599505bbe";
    String TWILIO_FROM_NUMBER="+13476951233";
    String OSS_BUCKET_NAME = "test-coros-";

}
