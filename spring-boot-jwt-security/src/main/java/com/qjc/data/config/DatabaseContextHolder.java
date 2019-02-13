package com.qjc.data.config;

public class DatabaseContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	 
	/**
	 * 设置数据源
	 * @param dataSourceKey
	 */
	public static void setDataSource(String dataSourceKey) {
		contextHolder.set(dataSourceKey);
	}
 
	/**
	 * 取的 当前数据源
	 * @return
	 */
	public static String getDataSource() {
		return contextHolder.get();
	}
 
	/**
	 * 清楚上下文数据源
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}
}
