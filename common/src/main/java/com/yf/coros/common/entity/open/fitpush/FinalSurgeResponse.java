package com.yf.coros.common.entity.open.fitpush;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * FinalSurge 返回类
 * @author Infi
 */
@Data
public class FinalSurgeResponse implements Serializable {
    private static final long serialVersionUID = -4086437114385746014L;
    @JSONField(alternateNames = "Success")
    private Boolean success;
    @JSONField(alternateNames = "ErrorNumber")
    private Integer errorNumber;
    @JSONField(alternateNames = "ErrorMessage")
    private String errorMessage;
}
