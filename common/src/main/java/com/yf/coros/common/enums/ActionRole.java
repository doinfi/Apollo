package com.yf.coros.common.enums;

/**
 * 操作权限：查询：1，修改：2，新增：4，删除：8，如果一个用户同事拥有新增和查询权限，那么 activate_role=5
 *
 * @author Infi
 */
public interface ActionRole {
    /**
     * 查询权限
     */
    Integer QUERY_ROLE = 1;
    /**
     * 修改权限
     */
    Integer UPDATE_ROLE = 2;
    /**
     * 新增权限
     */
    Integer ADD_ROLE = 4;
    /**
     * 删除权限
     */
    Integer DELETE_ROLE = 8;
}
