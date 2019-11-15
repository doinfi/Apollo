package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 轨迹对象
 *
 * @author Infi
 */
@Data
public class Track implements Serializable {
    private static final long serialVersionUID = -8104343939494489629L;
    private Long id;
    private String trackId;
    private BigInteger trackIdUnsignedLong;
    private Long userId;
    private Integer happenMonth;
    private String name;
    private Double distance;
    private Long timestamp;
    private Integer duration;
    private Integer avgPace;
    private Integer trackSize;
    private String trackUrl;
    private byte[] trackData;
    private String imageUrl;
    private Integer trackCrc;
    private Date createTime;
    private Date updateTime;
    private Integer count;
    private byte[] imageData;
    private String uuid;
    private Integer dataLength;
    private Integer state;
    private Integer modifyTime;
    private byte[] modifyTrackData;
}
