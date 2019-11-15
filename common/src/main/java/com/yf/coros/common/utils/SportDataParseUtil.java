package com.yf.coros.common.utils;

import com.yf.lib.w4.sport.W4FrequencyEntity;
import com.yf.lib.w4.sport.W4Value;
import org.apache.commons.collections4.CollectionUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lihuaijin
 */
public class SportDataParseUtil {

    /**
     * 从w4SportData中解析出开始到结束之间的有效值，并插值补齐所有秒，按时间顺序排序的map
     *
     * @param w4FrequencyEntitieList 待解析的数据结构List
     * @param startSec               开始时间戳秒
     * @param endSec                 结束时间戳秒
     * @param signed                 待解析的数据是否有符号，默认无符号
     * @return 解析出的map，秒与值映射
     */
    public static TreeMap<Long, Long> normalizeFrequencyEntities(List<W4Value<Long>> w4FrequencyEntitieList,
                                                                 long startSec, long endSec, Boolean... signed) {
        TreeMap<Long, Long> dataMap = new TreeMap<>();
        if (CollectionUtils.isEmpty(w4FrequencyEntitieList)) {
            return dataMap;
        }
        List<W4Value> w4ValueList = w4FrequencyEntitieList.stream()
                .filter(item -> item.getTimestampInSecond() >= startSec && item.getTimestampInSecond() <= endSec).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(w4ValueList)) {
            return new TreeMap<>();
        }
        for (W4Value w4Value : w4ValueList) {
            dataMap.put(w4Value.getTimestampInSecond(), (Long) w4Value.getValue());
        }
        // 补值
        fillWithNearbyLessKey(dataMap, startSec, endSec);
        return dataMap;
    }

    /**
     * 从w4SportData中解析出开始到结束之间的有效值，并插值补齐所有秒，按时间顺序排序的map
     *
     * @param w4FrequencyEntities 待解析的数据结构List
     * @param startSec            开始时间戳秒
     * @param endSec              结束时间戳秒
     * @param signed              待解析的数据是否有符号，默认无符号
     * @return 解析出的map，秒与值映射
     */
    public static TreeMap<Long, Long> normalizeFrequencyEntitiesOld(
            List<W4FrequencyEntity> w4FrequencyEntities, long startSec, long endSec, Boolean... signed) {
        TreeMap<Long, Long> dataMap = parseFrequencyEntities(w4FrequencyEntities, startSec, endSec, signed);
        fillWithNearbyLessKey(dataMap, startSec, endSec);
        return dataMap;
    }

    /**
     * 从w4SportData中解析出开始到结束之间的有效值，并插值补齐所有秒，按时间顺序排序的map
     *
     * @param w4FrequencyEntities 待解析的数据结构List
     * @param startSec            开始时间戳秒
     * @param endSec              结束时间戳秒
     * @param signed              待解析的数据是否有符号，默认无符号
     * @return 解析出的map，秒与值映射
     */
    public static TreeMap<Long, Long> parseFrequencyEntities(
            List<W4FrequencyEntity> w4FrequencyEntities, long startSec, long endSec, Boolean... signed) {
        TreeMap<Long, Long> dataMap = new TreeMap<>();
        if (null == w4FrequencyEntities) {
            return dataMap;
        }
        w4FrequencyEntities.forEach(o ->
                dataMap.putAll(parseFrequencyEntity(o, startSec, endSec, signed))
        );

        return dataMap;
    }

    /**
     * 从w4SportData中解析出开始到结束之间的有效值
     *
     * @param w4FrequencyEntity 待解析的数据结构
     * @param startSec          开始时间戳秒
     * @param endSec            结束时间戳秒
     * @param signed            待解析的数据是否有符号，默认无符号
     * @return 解析出的map，秒与值映射
     */
    public static Map<Long, Long> parseFrequencyEntity(
            W4FrequencyEntity w4FrequencyEntity, long startSec, long endSec, Boolean... signed) {
        long timestamp = w4FrequencyEntity.getTimestampInSecond();
        int interval = w4FrequencyEntity.getIntervalInSecond();
        boolean isSigned = null != signed && signed.length > 0 && signed[0];
        Map<Long, Long> tempMap = new HashMap<>();
        for (int i = 0; i < w4FrequencyEntity.getRatesCount() / w4FrequencyEntity.getItemSize(); i++) {

            ByteBuffer bb = ByteBuffer.wrap(w4FrequencyEntity.getRates(), i * w4FrequencyEntity.getItemSize(), w4FrequencyEntity.getItemSize())
                    .order(ByteOrder.LITTLE_ENDIAN);
            long value;
            switch (w4FrequencyEntity.getItemSize()) {
                case 1: {
                    value = isSigned ? bb.get() : 0xFF & bb.get();
                    break;
                }
                case 2: {
                    value = isSigned ? bb.getShort() : 0xFFFF & bb.getShort();
                    break;
                }
                case 4: {
                    value = isSigned ? bb.getInt() : 0x0FFFFFFFFL & bb.getInt();
                    break;
                }
                case 8: {
                    value = bb.getLong();
                    break;
                }

                // 无效值
                default: {
                    value = 0;
                }
            }

            for (int j = 0; j < interval; j++) {
                if (timestamp >= startSec && timestamp <= endSec) {
                    tempMap.put(timestamp, value);
                }
                timestamp++;
            }
        }
        return tempMap;
    }

    /**
     * 将周期不为1秒或存在空缺的数据采用前位插值的方式整合为周期为秒的数据集
     *
     * @param map      source map
     * @param startKey start
     * @param endKey   end
     * @param <T>      Type
     */
    public static <T> void fillWithNearbyLessKey(TreeMap<Long, T> map, Long startKey, Long endKey) {
        if (map.isEmpty()) {
            return;
        }
        TreeSet<Long> set = new TreeSet<>(map.keySet());
        long sourceFirst = set.first();
        long sourceLast = set.last();
        // 补全起点
        if (startKey < sourceFirst) {
            T firstValue = map.get(sourceFirst);
            for (long i = startKey; i < sourceFirst; i++) {
                map.put(i, firstValue);
            }
        }
        // 补全终点
        if (endKey > sourceLast) {
            T lastValue = map.get(sourceLast);
            for (long i = sourceLast + 1; i <= endKey; i++) {
                map.put(i, lastValue);
            }
        }
        T fillValue = map.get(sourceFirst);
        // 然后从map初始的起点遍历到终点来插值，因为treeMap是从小到大key排序的，因此把缺省值补充为上一次的值就Ok了
        for (long i = sourceFirst + 1; i < sourceLast; i++) {
            if (set.contains(i)) {
                fillValue = map.get(i);
            } else {
                map.put(i, fillValue);
            }
        }
    }

    /**
     * 采用前位插值的方式填满数据集，第一个值之前的值全部预设为第一个值
     *
     * @param map     source map
     * @param allKeys 所有待补全的key
     * @param <T>     Type
     */
    public static <T> void fillWithNearbyLessKey(
            TreeMap<Integer, T> map, TreeSet<Integer> allKeys) {
        if (map.isEmpty()) {
            return;
        }
        TreeSet<Integer> set = new TreeSet<>(map.keySet());
        int sourceFirst = set.first();
        T defVal = map.get(sourceFirst);
        T fillValue = map.get(sourceFirst);

        // 补全起点和终点，并前项插值
        for (Integer key : allKeys) {
            // key在map第一个值前面，则补第一个值
            if (key < sourceFirst) {
                map.put(key, defVal);
                continue;
            }
            // 如果map中有，则fillValue更新
            if (map.containsKey(key)) {
                fillValue = map.get(key);
            } else {
                // map中没有，则补充到map中
                map.put(key, fillValue);
            }
        }

    }

}
