package com.yf.coros.common.entity.appprofile;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * APP 配置信息
 *
 * @author Infi
 */
@Data
public class AppProfile  implements Serializable{

    private static final long serialVersionUID = 6432661798837268169L;
    private Long id;
    private Long appVersion;
    private Integer appType;
    private Integer releaseType;
    private Integer dataType;
    private Integer subDataType;
    private String jsonString;
    private Date createTime;
    private Date updateTime;
}
