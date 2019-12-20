package com.apollo.common.entity.enums;

/**
 * 状态结果枚举定义
 * 1. 与丁慧 ReturnMessage 重合, 讨论优化。
 * 2. 确保定义的result 与旧版的保持一致。
 * <p> 返回编码结构定义:
 * x-xxx或者xx-xxx,[模块][错误]。
 * 编码结构说明:
 * 1. 如果是四位编码,第一位表示模块;如果是五位编码,前两位表示模块。
 * 2.
 * 在错误编码中模块可以是业务逻辑上的"模块",比如说公共模块,如果有必要可以分为手环绑定"模块",验证码"模块"
 * 3. 最后三位表示错误编号。
 * <p> 返回编号分段定义:
 * 1. 0000: 表示操作成功
 * 2. 1001- 1999: 表示系统错误信息
 * @author infi
 */
public interface MessageKey {
    String RETURN_OK = "0000";
    String SYSTEM_ERROR = "1001";
    String REQUEST_EXCEPTIONS_OR_PARAMETER_ERROR = "1009";
}
