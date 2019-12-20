package com.apollo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * 与Client端交互用的实体类，补充扩展字段extData
 *
 * @author lihuaijin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseVOExtension extends ResponseVO {

    private static final long serialVersionUID = 4811760388211208137L;

    public ResponseVOExtension() {
        super();
    }

    public ResponseVOExtension(String messageKey) {
        super(messageKey);
    }

    public ResponseVOExtension(ResponseVO responseVO) {
        BeanUtils.copyProperties(responseVO, this);
    }

    private Object extData;
}
