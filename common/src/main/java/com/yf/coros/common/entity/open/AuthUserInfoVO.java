package com.yf.coros.common.entity.open;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA
 * Created by ClearWang on 2017/9/27.
 */
@Getter
@Setter
public class AuthUserInfoVO implements Serializable{
	 
	private static final long serialVersionUID = -2330996320552319922L;
	private String openId;
    private String nick;
    private String profilePhoto;
    private String runDistance;
    private String runTotalTime;
    private String runCalorie;
    private String firstName;
    private String lastName;
    private String email;
}
