package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 轨迹查询对象
 *
 * @author Infi
 */
@Data
public class TrackCriteria implements Serializable {
    private static final long serialVersionUID = 3521390508024056556L;
    private Long id;
    private String trackId;
    private List<Track> trackList;
    private Long userId;
}
