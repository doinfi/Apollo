package com.yf.coros.common.entity.cep;

import lombok.Data;

import java.io.Serializable;

/**
 * cep文件检查返回对象
 * @author Infi
 */
@Data
public class CepCheckValue implements Serializable {
    private static final long serialVersionUID = -2267640752176763773L;
    private Integer delayDays;
}
