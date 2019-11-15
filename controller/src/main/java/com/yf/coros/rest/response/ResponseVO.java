package com.yf.coros.rest.response;

import com.alibaba.fastjson.JSON;
import com.ibm.icu.util.LocaleMatcher;
import com.ibm.icu.util.ULocale;
import com.yf.coros.common.entity.yfheader.YFHeader;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

import com.yf.coros.common.utils.yfheader.YFHeaderUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 与Client端交互用的实体类
 *
 * @author infi.wang
 */
@Getter
@Setter
public class ResponseVO implements Serializable {

    private static final long serialVersionUID = 6010481115038758220L;

    private static final String RETURN_OK = "0000";

    private String result;
    private String message;
    private Object data;

    public ResponseVO() {
        this.result = RETURN_OK;
        this.message = getMessageByKey(RETURN_OK);
    }

    public ResponseVO(String messageKey) {
        this.result = messageKey;
        this.message = getMessageByKey(messageKey);
    }

    /**
     * TODO 提示语言，中文乱码 根据key获取信息
     * 配置文件为了防止乱码，properties文件里3个字节以上表示的字符（例如中文）一般使用unicode配置。
     * TITLE=\u6211\u7684\u82f9\u679c
     *
     * @param messageKey key值
     * @return 信息
     */

    private String getMessageByKey(String messageKey) {
        // TODO 后期可以考虑使用用自动注入的方式获得request @Autowired HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String language = "en-US";
        String languageForHeader = YFHeaderUtils.getHeaderLanguage(request);
        if (StringUtils.isNotBlank(languageForHeader)) {
            language = languageForHeader;
        }

        LocaleMatcher localeMatcher = new LocaleMatcher("en-US,zh-CN");
        ULocale uLocale = localeMatcher.getBestMatch(language);
        String matchLanguage = uLocale.toLanguageTag();

        String languageStr = StringUtils.substringBefore(matchLanguage, "-").toLowerCase();
        String country = StringUtils.substringAfter(matchLanguage, "-").toUpperCase();
        Locale locale = new Locale(languageStr, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);
    }
}
