package com.yf.coros.common.utils.cache;

import com.yf.coros.common.entity.cep.CepCache;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 告警数据缓存
 *
 * @author Infi
 */
public class AlertCacheUtils {
    private static AlertCacheUtils instance = new AlertCacheUtils();

    private Map<String, CepCache> cepMap = new ConcurrentHashMap<>();

    private final Object LOCK = new Object();

    private AlertCacheUtils() {
    }

    /**
     * 获取本类单例
     *
     * @return 实例
     */
    public static AlertCacheUtils getInstance() {
        return instance;
    }

    /**
     * 新增缓存
     *
     * @param key      key
     * @param cepCache cep缓存
     */
    public void put(String key, CepCache cepCache) {
        if (cepCache == null || CollectionUtils.isEmpty(cepCache.getSonyCepList())) {
            return;
        }
        synchronized (LOCK) {
            cepMap.put(key, cepCache);
        }
    }

    /**
     * 获取cep缓存
     *
     * @param key key
     * @return cep缓存数据
     */
    public CepCache get(String key) {
        return cepMap.get(key);
    }

}
