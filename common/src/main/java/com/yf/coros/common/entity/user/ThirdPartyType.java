package com.yf.coros.common.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方帐号类型
 *
 * @author Infi
 */
public interface ThirdPartyType {
    int STRAVA = 1;
    int TP = 2;
    int RQ = 3;
    int RELIVE = 4;
    int FINAL_SURGE = 5;
    int RUNALYZE = 6;
}
