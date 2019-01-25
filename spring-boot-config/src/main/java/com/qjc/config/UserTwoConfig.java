package com.qjc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qjc
 * @date 2019年1月24日下午2:31:33
 * @version 1.0.0
 */
@Component
public class UserTwoConfig {
	/**
	 * 姓名
	 */
	@Value("${spring.boot.name}")
	private String name;
	/**
	 * 性别
	 */
	@Value("${spring.boot.sex}")
	private String sex;
	/***
	 * 年龄
	 */
	@Value("${spring.boot.age}")
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}
