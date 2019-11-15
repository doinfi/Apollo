package com.yf.coros.common.enums;

/**
 * 验证码类型枚举类<br> 枚举说明 枚举类只读<br> RESET_PWD 表示重置密码,<br> REGISTER 表示用户注册,<br>
 *
 * @author Infi
 * @date 17/2/19
 */
public interface CheckCodeActionTypeEnum {

    /**
     * 注册
     */
    int REGISTER = 1;
    /**
     * 重置密码
     */
    int RESET_PWD = 2;
    /**
     * 邮箱地址激活
     */
    int EMAIL_ACTIVATE = 3;

    /**
     * 帐号绑定
     */
    int ACCOUNT_BIND = 4;
    /**
     * 密码错误日志
     */
    int PWD_ERROR_LOG = 5;
}
