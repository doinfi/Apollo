package com.yf.coros.common.entity.data;

import com.yf.coros.common.enums.DataParseRecordConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
@NoArgsConstructor
public class SportDataParseRecord implements Serializable {

    private static final long serialVersionUID = -7383466096536816436L;

    /**
     * 构造方法，多参
     * @param userId 用户ID
     * @param sportFileName 运动文件名
     * @param sportFileUuid 运动uuid
     * @param parseType 解析类型
     * @param workId 服务ID
     */
    public SportDataParseRecord(Long userId, String sportFileName, String sportFileUuid, Integer parseType, Integer workId) {
        this.userId = userId;
        this.sportFileName = sportFileName;
        this.sportFileUuid = sportFileUuid;
        this.parseType = parseType;
        this.workId = workId;
        this.createTime = System.currentTimeMillis() / 1000;
        this.status = DataParseRecordConstants.RUNNING;
    }

    /**
     * 构造方法，labelData主参
     * @param labelData labelData
     * @param parseType 解析类型
     * @param workId 服务ID
     */
    public SportDataParseRecord(LabelData labelData, Integer parseType, Integer workId) {
        this(labelData.getUserId(), null != labelData.getSportFileName() ? labelData.getSportFileName() : String.valueOf(labelData.getLabelId()),
                labelData.getUuid(), parseType, workId);
    }

    private Long id;
    private Long userId;
    private String sportFileName;
    private String sportFileUuid;
    private Integer workId;
    private Integer parseType;
    private Integer status;
    private Long createTime;
    private Long updateTime;
}
