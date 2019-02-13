package com.qjc.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 定义user对象
 * @author qjc
 * @date 2019年2月11日下午3:34:25
 * @version 1.0.0
 */
public class SelfUserDetails implements UserDetails, Serializable{

	/**
	 * @author qjc
	 * @date 2019年2月11日下午3:35:03
	 */
	private static final long serialVersionUID = -4174888841192832739L;

    private Integer id;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
	    return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		 return this.username;
	}

	/**
	 * 账号是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 账号是否锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 账号凭证是否未过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
