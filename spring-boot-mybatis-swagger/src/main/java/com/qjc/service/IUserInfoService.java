package com.qjc.service;
/**
 * @author qjc
 * @date 2019年1月24日下午4:19:06
 * @version 1.0.0
 */

import java.util.List;

import com.qjc.entry.UserInfo;

public interface IUserInfoService {
	/**
	 * 根据ID查询某个用户信息
	 * @param id
	 * @return
	 */
	public UserInfo findUserInfoById(Long id);
	
	/**
	 * 查询全部用户的姓名
	 * @return
	 */
	public List<String> findUserAllByName();
}
