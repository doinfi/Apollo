package com.apollo.config;

import com.apollo.common.exception.YfException;
import com.apollo.common.primary.YFPrimaryKeyUtils;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/21
 */
public class PrimaryKey {
    public static long getId(Integer modeId) throws YfException {
        return YFPrimaryKeyUtils.getId(modeId,1L);
    }
}
