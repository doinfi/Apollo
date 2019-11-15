package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 将时间戳秒转化为yyyy-MM-dd HH:mm:ss
 * @author lihuaijin
 */
@Slf4j
public class DateTimeSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (null == object) {
            serializer.out.writeNull();
            return;
        }

        if (object instanceof Long) {

            DateTime time = new DateTime(((Long) object) * 1000, DateTimeZone.UTC);
            serializer.out.writeString(time.toString("yyyy-MM-dd HH:mm:ss"));
        }
        else {
            serializer.out.writeString(object.toString());
        }
    }
}