package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

/**
 * @author lihuaijin
 */
public abstract class AbstractObjectDeserializer implements ObjectDeserializer {

    public AbstractObjectDeserializer(){
        super();
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
