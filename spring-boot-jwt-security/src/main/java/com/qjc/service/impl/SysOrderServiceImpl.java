package com.qjc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjc.annotation.DataSource;
import com.qjc.entry.SysOrder;
import com.qjc.enums.DataSourceEnum;
import com.qjc.mapper.SysOrderMapper;
import com.qjc.service.ISysOrderService;

/**
 * @author qjc
 * @date 2019年1月28日下午5:08:26
 * @version 1.0.0
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService {

	@Autowired
	SysOrderMapper  sysOrderMapper;
	
	
	@Override
	@DataSource(DataSourceEnum.DB2)
	public SysOrder findSysOrderById(Long id) {
		return sysOrderMapper.selectByPrimaryKey(id);
	}

}
