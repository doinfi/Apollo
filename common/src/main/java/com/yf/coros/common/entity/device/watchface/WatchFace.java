package com.yf.coros.common.entity.device.watchface;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表盘工具
 *
 * @author Infi
 */
@Data
public class WatchFace implements Serializable {
    private static final long serialVersionUID = -5169070945176004541L;
    private Long id;
    private Long watchFaceId;
    private String resolution;
    private Integer releaseType;
    private String firmwareType;
    private String watchFaceAuthor;
    private String watchFaceName;
    private Integer watchFaceType;
    private String imageUrl;
    private String watchFaceUrl;
    private Integer crc;
    private Long createTimestamp;
    private Date createTime;
    private byte[] watchFaceFileBytes;
    private String idStr;
    private String creator;
    private Date deleteTime;
    private String deleter;
    //    语言包含：中文简体（zh-CN），英文（en-US），德语（de-DE），法语（fr-FR），意大利语（it-IT），西班牙语（es-ES），印尼语（id-ID），日语（jp-JP）
    private String watchFaceNameZh;
    private String watchFaceNameEn;
    private String watchFaceNameDe;
    private String watchFaceNameEs;
    private String watchFaceNameFr;
    private String watchFaceNameIt;
    private String watchFaceNameId;
    private String watchFaceNameJp;
    private String watchFaceNameDefaultLanguage;
}
