package com.yf.coros.common.entity.aligenie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * AliGenie平台参数
 *
 * @author Infi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliGenieProperties implements Serializable {
    private static final long serialVersionUID = -663549868832701464L;
    private String name;
    private String value;

}
