package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * 勋章规则表
 *
 * @author Infi
 * @date 17/6/13
 */
@Data
public class MedalRule implements Serializable {

    private static final long serialVersionUID = -6491244194024429078L;
    private Integer id;
    private String name;
    private Double standardValue;
    private String detail;
    private Integer medalId;
    private Integer mode;
    private Integer medalType;
    private Integer subMode;
}
