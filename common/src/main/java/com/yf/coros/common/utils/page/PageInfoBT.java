package com.yf.coros.common.utils.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 * @author dinghui
 * @date 2019/7/4 17:11
 */

public class PageInfoBT<T> implements Serializable {

    private static final long serialVersionUID = -4859791036554638612L;
    // 结果集
    private List<T> rows;

    // 总数
    private long total;

    /**
     * 当前页
     */
    private int page = 1;

    public PageInfoBT() {
        super();
    }

    public PageInfoBT(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
        this.page = page.getCurrent();
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
