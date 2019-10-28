package com.ecjtu.dynamicdatasource.config.datasource;

/**
 * @author xiexiang
 * @date 2019/10/25 2:52 下午
 */

public class DbContextHolder {
    /**
     * 存储不同数据源的名称
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        CONTEXT_HOLDER.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDbType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }

}
