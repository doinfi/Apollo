package com.yf.coros.common.entity.cep;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * cep 数据缓存实体对象
 *
 * @author Infi
 */
@Data
public class CepCache implements Serializable {
    private static final long serialVersionUID = 5557358281634428004L;
    private List<SonyCep> sonyCepList;
}
