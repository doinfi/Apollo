package com.yf.coros.common.entity.cep;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * cep文件信息
 *
 * @author Infi
 */
@Data
public class CepFile implements Serializable {
    private static final long serialVersionUID = 1798598114209385058L;
    @JSONField(serialize = false)
    private byte[] cepFile;
    private Long timestamp;
    private String md5Code;
    private String fileName;
    private byte[] file;
}
