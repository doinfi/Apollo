package com.yf.coros.common.entity.data.analyse.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 将double保留2位有效数字
 * @author lihuaijin
 */
public class DoubleFormatScaleSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (null == object) {
            serializer.out.writeNull();
        }
        if (object instanceof String || object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double) {
            BigDecimal bd = new BigDecimal(String.valueOf(object)).setScale(2, BigDecimal.ROUND_HALF_UP);
            serializer.out.writeString(new DecimalFormat("#.00").format(bd.doubleValue()));
        }

    }
}
