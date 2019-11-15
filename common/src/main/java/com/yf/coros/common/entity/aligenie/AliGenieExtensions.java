package com.yf.coros.common.entity.aligenie;

import lombok.Data;

import java.io.Serializable;

/**
 * ALiGenie平台payload部分的extensions内容
 *
 * @author Infi
 */
@Data
public class AliGenieExtensions implements Serializable {
    private static final long serialVersionUID = -8417277221704808371L;
    private String extension1;
    private String extension2;
    private String parentId;
}
