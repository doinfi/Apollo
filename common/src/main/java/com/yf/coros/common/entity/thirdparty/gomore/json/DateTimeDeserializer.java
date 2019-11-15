package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Type;

/**
 * 将yyyy-MM-dd HH:mm:ss转化为时间戳秒
 * @author lihuaijin
 */
@Slf4j
public class DateTimeDeserializer extends AbstractObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        String strVal = parser.getLexer().stringVal();
        try {
            //noinspection unchecked
            return (T) (Long) (DateTime.parse(parser.getLexer().stringVal(),
                    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZoneUTC()).getMillis() / 1000);
        } catch (Exception e) {
            log.warn("parse date time error. key: " + fieldName + ", value: " + strVal, e);
        }
        return null;
    }
}