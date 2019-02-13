package com.qjc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjc.entry.UserInfo;
import com.qjc.mapper.UserInfoMapper;
import com.qjc.service.IUserInfoService;

/**
 * @author qjc
 * @date 2019年1月24日下午4:20:25
 * @version 1.0.0
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService  {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Override
	public UserInfo findUserInfoById(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

}
