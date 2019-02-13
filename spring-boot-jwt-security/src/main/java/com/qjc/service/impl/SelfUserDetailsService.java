package com.qjc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qjc.entry.UserInfo;
import com.qjc.entry.UserInfoExample;
import com.qjc.entry.UserInfoExample.Criteria;
import com.qjc.mapper.UserInfoMapper;
import com.qjc.vo.SelfUserDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qjc
 * @date 2019年2月11日下午4:34:59
 * @version 1.0.0
 */
@Component
@Slf4j
public class SelfUserDetailsService implements UserDetailsService {

	@Autowired
	UserInfoMapper userInfoMapper;

	@SuppressWarnings({ "unused", "rawtypes", "unchecked", "null" })
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SelfUserDetails user  = new SelfUserDetails();;
		UserInfoExample example = new UserInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		example.setOrderByClause("create_time DESC");
		List<UserInfo> userInfoLst = userInfoMapper.selectByExample(example);
		if (null != userInfoLst && userInfoLst.size() > 0) {
			UserInfo userInfo = userInfoLst.get(0);
			log.info("用户：{}",JSONObject.toJSON(userInfo));
			user.setId(userInfo.getId().intValue());
			user.setPassword(userInfo.getPassword());
			user.setUsername(userInfo.getUsername());
			if (user == null) {
				// 仍需要细化处理
				throw new UsernameNotFoundException("该用户不存在");
			}
		}
		Set authoritiesSet = new HashSet();
		// 模拟从数据库中获取用户角色
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

		authoritiesSet.add(authority);
		user.setAuthorities(authoritiesSet);
		log.info("用户2：{}",JSONObject.toJSON(user));
		return user;
	}

}
