package com.apollo.common.exception;

import com.apollo.common.entity.enums.MessageKey;
import com.apollo.common.utils.YfTools;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;

/**
 * 自定义系统异常类
 *
 * @author infi.wang
 */
@Getter
@Setter
public class YfException extends Exception implements Serializable {

    private static final long serialVersionUID = -8436383747577940435L;
    private String result;
    private MessageKey returnMessage;

    /**
     * 构造方法
     */
    public YfException() {
    }

    /**
     * 自定义业务异常
     *
     * @param messageKey 消息编号
     */
    public YfException(String messageKey) {
        super(YfTools.getMessageByKey(messageKey));
        this.setResult(messageKey);
    }

    /**
     * 自定义业务异常
     *
     * @param e 异常信息
     * @param messageKey 消息编号
     */
    public YfException(Exception e, String messageKey) {
        super(YfTools.getMessageByKey(messageKey), e);
        this.setResult(messageKey);
    }

    /**
     * 自定义业务异常
     *
     * @param result  消息编号
     * @param message 消息
     */
    public YfException(String result, String message) {
        super(message);
        this.setResult(result);
    }


    /**
     * 自定义业务异常
     *
     * @param e       异常信息
     * @param result  消息编号
     * @param message 占位符中参数
     */
    public YfException(Exception e, String result, String message) {
        super(message, e);
        this.setResult(result);
    }

    /**
     * 多语言版本异常返回
     *
     * @param result 错误码
     * @param locale 语言本地化
     */
    public YfException(String result, Locale locale) {
        super(YfTools.getMessageByKey(result, locale));
        this.setResult(result);
    }
}
