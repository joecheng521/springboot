package com.qjc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.qjc.mapper")
@EnableTransactionManagement
public class SpringBootMybatisDruidApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisDruidApplication.class, args);
	}

}

