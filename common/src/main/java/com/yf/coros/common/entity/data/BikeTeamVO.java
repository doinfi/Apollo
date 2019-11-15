package com.yf.coros.common.entity.data;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 自行车导航团队实体类
 *
 * @author Infi
 * @date 17/5/12
 */
@Data
public class BikeTeamVO {

    private Long teamId;
    private Long teamUpdateTime;
    private List<BikeTeamUser> userList = new ArrayList<>();
    private Boolean allUserFlag;
}
