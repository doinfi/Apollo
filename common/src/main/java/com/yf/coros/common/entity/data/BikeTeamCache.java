package com.yf.coros.common.entity.data;

import com.yf.coros.common.entity.data.BikeTeamUser;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * 团队缓存对象
 *
 * @author Infi
 * @date 17/5/17
 */
@Data
public class BikeTeamCache {

    private long teamId;
    private Map<Long, BikeTeamUser> userDataMap = new HashMap<>();
    private long teamUpdateTime;
}
