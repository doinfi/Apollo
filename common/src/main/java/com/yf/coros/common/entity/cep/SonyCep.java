package com.yf.coros.common.entity.cep;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * sony cep 数据类
 *
 * @author Infi
 */
@Data
public class SonyCep implements Serializable {
    private static final long serialVersionUID = -8954013951929255386L;
    private Integer type;
    private String url;
    private Long timestamp;
    private String md5;
    private Integer happenDay;
    @JSONField(serialize = false)
    private String checkCode;
    /**
     * cep文件下载目录
     */
    @JSONField(serialize = false)
    private String fileDirectory;
    /**
     * cep文件下载名称
     */
    @JSONField(serialize = false)
    private String fileName;
    /**
     * cep校验码文件名称
     */
    @JSONField(serialize = false)
    private String checkCodeFileName;
    /**
     * cep文件校验类型
     */
    @JSONField(serialize = false)
    private Integer checkCodeType;
    /**
     * cep文件本地存储目录
     */
    @JSONField(serialize = false)
    private String fileDirectory2Local;
    /**
     * 本地的验证码文件名称
     * gln和gps文件，sony服务器没有md5校验码，保存到本地要增加md5校验码文件
     */
    @JSONField(serialize = false)
    private String checkCodeFileName2Local;
}
