package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;

import java.lang.reflect.Type;

/**
 * @author lihuaijin
 */
public class NewUserDeserializer extends AbstractObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        //noinspection unchecked
        return (T) (Boolean) ("1".equals(parser.getLexer().stringVal()));
    }
}
