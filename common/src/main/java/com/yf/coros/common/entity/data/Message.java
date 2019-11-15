package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -3173917379414747430L;
    private Long id;
    private Long time;
    private String title;
    private String subtitle;
    private String imageUrl;
    private String detailUrl;
    private String countryCode;
    private Integer clientType;
    private String firmwareType;
    private byte[] imageBytes;
    private Long creator;
    private Date createTime;
    private String idString;
    private Integer welcome;
    private String language;
    private Integer isDefault;
}
