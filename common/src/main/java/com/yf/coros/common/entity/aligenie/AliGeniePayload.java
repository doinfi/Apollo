package com.yf.coros.common.entity.aligenie;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * AliGenie平台请求也已payload部分
 *
 * @author Infi
 */
@Data
public class AliGeniePayload implements Serializable {
    private static final long serialVersionUID = 2865109824212460960L;
    private String accessToken;
    private String deviceId;
    private String deviceType;
    private String attribute;
    private String value;
    private AliGenieExtensions extensions;
    private List<AliGenieDevice> devices;
    private List<String> actions;
}
