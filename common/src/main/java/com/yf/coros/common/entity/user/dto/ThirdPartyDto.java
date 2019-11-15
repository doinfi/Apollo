package com.yf.coros.common.entity.user.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ThirdPartyDto implements Serializable {

    private static final long serialVersionUID = -1033280161895214356L;
    private Integer thirdPartyId;
    private List<Integer> thirdPartyIdList;
}
