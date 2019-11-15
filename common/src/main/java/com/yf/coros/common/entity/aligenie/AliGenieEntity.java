package com.yf.coros.common.entity.aligenie;

import lombok.Data;

import java.io.Serializable;

/**
 * AliGenie平台协议
 *
 * @author Infi
 */
@Data
public class AliGenieEntity implements Serializable {
    private static final long serialVersionUID = -3825028135307005847L;
    private AliGenieHeader header;
    private AliGeniePayload payload;
}
