package com.yf.coros.common.entity.appprofile;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * APP 配置信息
 *
 * @author Infi
 */
@Data
public class AppProfileCriteria implements Serializable {

    private static final long serialVersionUID = -5034940251039289037L;
    private Long appVersion;
    private Integer appType;
    private Integer releaseType;
    private Integer dataType;
    private Integer subDataType;
    private List<AppProfile> dataList;
}
