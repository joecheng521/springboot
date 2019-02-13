package com.qjc.service;

import com.qjc.entry.SysOrder;

/**
 * @author qjc
 * @date 2019年1月28日下午2:42:05
 * @version 1.0.0
 */
public interface ISysOrderService {
	/**
	 * 根据ID查询某个订单
	 * @param id
	 * @return
	 */
	public SysOrder findSysOrderById(Long id);
}
