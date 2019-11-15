package com.yf.coros.common.entity.data.stride;

import lombok.Data;

import java.util.Date;

/** 可训练数据 */
@Data
public class StrideLengthTrain {
  private Long userId;
  private byte[] trainData;
  private Date updateTime;
}
