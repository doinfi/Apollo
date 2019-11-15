package com.yf.coros.common.enums;

/**
 * 帐号注册类型枚举类<br> 枚举说明 枚举类只读<br> MOBILE 表示使用电话号码注册,<br> EMAIL 表示使用email注册,<br> FACEBOOK
 * 表示使用facebook注册,<br> WEIXIN 表示使用微信注册<br>
 *
 * @author Infi
 * @date 17/2/19
 */
public interface AccountTypeEnum {

    /**
     * 电话号码
     */
    int MOBILE = 1;
    /**
     * 邮箱地址
     */
    int EMAIL = 2;
    /**
     * Facebook
     */
    int FACEBOOK = 3;
    /**
     * 微信
     */
    int WEIXIN = 4;
}
