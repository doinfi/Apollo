package com.yf.coros.common.entity.thirdparty.gomore.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * k单位的Double型
 * @author lihuaijin
 */
public class KiloDoubleSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (null == object) {
            serializer.out.writeNull();
        }
        if (object instanceof String || object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double) {
            BigDecimal bd = new BigDecimal(String.valueOf(object));
            serializer.out.writeDouble(bd.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP).doubleValue(), false);
        }

    }
}
