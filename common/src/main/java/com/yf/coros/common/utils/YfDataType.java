/**
 * @author administrator
 * @date 2011-12-15
 * @package com.lingdian.tools.data
 * @class DataType.java
 */
package com.yf.coros.common.utils;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * 数据对象操作类,封闭对类型的判断,数据类型的转换. 对转换类型按规则执行,转换失败取默认转换值.
 *
 * @author administrator
 * @version V100R100C01
 * @package com.lingdian.tools.data
 * @class DataType.java
 */
public class YfDataType {

    /**
     * 默认私有构造函数,不允许创建此类的实例.
     */
    private YfDataType() {
    } // End DataType

    /**
     * 判断数据类型是否为字符类型
     *
     * @param value 数据对象
     * @return boolean true:字符对象; false:非字符对象;
     */
    public static boolean isChar(Object value) {
        return value instanceof Character;
    } // End isChar

    /**
     * 判断数据类型是否为字符串类型
     *
     * @param value 数据对象
     * @return boolean true:字符串对象; false:非字符串对象;
     */
    public static boolean isString(Object value) {
        return value instanceof String || value instanceof StringBuffer
            || value instanceof StringBuilder;
    } // End isString

    /**
     * 判断数据类型是否为空
     *
     * @param value 数据对象
     * @return boolean true:空对象;false:非空对象;
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object value) {
        boolean result = false;
        // 对象为null时,返回true
        if (null == value) {
            result = true;
        }

        // 字符串长度为0时,返回true,否则返回false
        else if (isString(value) || isChar(value)) {
            result = (value + "").trim().length() == 0;
        }

        // 集合，则判断长度是否为0
        else if (value instanceof Collection) {
            result = ((Collection) value).size() == 0;
        }

        // 集合，则判断长度是否为0
        else if (value instanceof Map) {
            result = ((Map) value).size() == 0;
        }

        //文件，判断文件是否存在
        else if (value instanceof File) {
            result = !((File) value).exists();
        }

        // 需要时再增加判断
        return result;
    } // End isEmpty


}