package com.qjc.security;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * 权限访问控制
 * 
 * @author qjc
 * @date 2019年2月11日下午3:39:27
 */
@Component("rbacauthorityservice")
public class RbacAuthorityService {
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object userInfo = authentication.getPrincipal();

		boolean hasPermission = false;

		if (userInfo instanceof UserDetails) {
			// 获取资源
			Set<String> urls = new HashSet<String>();
			// 这些 url 都是要登录后才能访问，且其他的 url 都不能访问！
			urls.add("/spring-boot-jwt-security/**");
			AntPathMatcher antPathMatcher = new AntPathMatcher();

			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}

			return hasPermission;
		} else {
			return false;
		}
	}
}
