package com.yf.coros.common.utils.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * excel 导出 entity
 *
 * @author Infi
 */
@Data
public class ExcelDataEntity implements Serializable {
    private static final long serialVersionUID = -6716060668034597952L;
    /**
     * 表头
     */
    private List<String> titleList;

    /**
     * 数据
     */
    private List<List<Object>> rowList;

    /**
     * 页签名称
     */
    private String name;
}
