package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * 勋章实体
 *
 * @author Infi
 * @date 17/6/13
 */
@Data
public class MedalCategory implements Serializable {

    private static final long serialVersionUID = 7967341735260174345L;
    private Integer id;
    private String name;
    private String detail;
}
