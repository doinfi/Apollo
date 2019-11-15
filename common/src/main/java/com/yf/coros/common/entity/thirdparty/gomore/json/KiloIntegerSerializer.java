package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * k单位的整型，四舍五入
 * @author lihuaijin
 */
public class KiloIntegerSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (null == object) {
            serializer.out.writeNull();
        }
        if (object instanceof String || object instanceof Integer || object instanceof Long || object instanceof Float) {
            BigDecimal bd = new BigDecimal(String.valueOf(object));
            serializer.out.writeLong(bd.divide(new BigDecimal(1000), 0, BigDecimal.ROUND_HALF_UP).longValue());
        }
    }
}
