package com.yf.coros.common.entity.cep;

import lombok.Data;

import java.io.Serializable;

/**
 * cep 检查返回结果
 * @author Infi
 */
@Data
public class CepCheckResponse implements Serializable {
    private static final long serialVersionUID = -767642772460498302L;
    private String result;
    private String message;
    private CepCheckValue data;
}
