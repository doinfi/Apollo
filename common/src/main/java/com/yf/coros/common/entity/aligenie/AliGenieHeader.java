package com.yf.coros.common.entity.aligenie;

import lombok.Data;

import java.io.Serializable;

/**
 * AliGenie平台请求协议头部信息
 *
 * @author Infi
 */
@Data
public class AliGenieHeader implements Serializable {
    private static final long serialVersionUID = 6361474561783141746L;
    private String namespace;
    private String name;
    private String messageId;
    private Integer payLoadVersion;

}
