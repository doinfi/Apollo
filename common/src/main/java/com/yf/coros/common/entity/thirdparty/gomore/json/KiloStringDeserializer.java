package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * 将单位转化为k
 * @author lihuaijin
 */
@Slf4j
public class KiloStringDeserializer extends AbstractObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        String s = parser.getLexer().stringVal();
        if (null != s && s.length() > 0) {
            try {
                BigDecimal deVal = new BigDecimal(s);
                BigDecimal bigDecimal = deVal.multiply(new BigDecimal(1000));
                if (bigDecimal.compareTo(new BigDecimal(Long.MAX_VALUE)) >= 0) {
                    log.warn("number value out of range.");
                    //noinspection unchecked
                    return (T) deVal.toPlainString();
                }
                //noinspection unchecked
                return (T) String.valueOf(bigDecimal.longValue());
            } catch (NumberFormatException e) {
                log.warn("parse double failed. key: " + fieldName + ", value: " + s, e);
                //noinspection unchecked
                return (T) s;
            }
        }
        return null;
    }
}
