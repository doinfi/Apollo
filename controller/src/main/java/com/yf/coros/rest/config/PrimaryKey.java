package com.yf.coros.rest.config;

import com.yf.coros.common.exception.YfException;
import com.yf.coros.common.utils.primary.YFPrimaryKeyUtils;

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
        return YFPrimaryKeyUtils.getId(modeId,Constants.WORK_ID);
    }
}
