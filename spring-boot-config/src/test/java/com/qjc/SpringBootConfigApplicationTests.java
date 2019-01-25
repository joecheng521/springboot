package com.qjc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qjc.config.UserOneConfig;
import com.qjc.config.UserTwoConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigApplicationTests {

	@Autowired
	UserOneConfig userOneConfig;

	@Autowired
	UserTwoConfig userTwoConfig;

	@Test
	public void contextLoads() {
		System.out.println(
				"name:" + userOneConfig.getName() + "sex:" + userOneConfig.getSex() + "sex:" + userOneConfig.getAge());
		System.out.println("name:" + userTwoConfig.getName() + "sex:" + userTwoConfig.getSex() + "sex:"
				+ userTwoConfig.getAge());
	}

}
