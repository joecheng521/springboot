package com.qjc.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qjc
 * @date 2019年1月24日下午4:12:03
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
//@Profile({"dev","test"})
//@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2 {
	// swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 为当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.qjc.controller"))
				.paths(PathSelectors.any()).build();
	}

	// 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("Spring Boot 测试使用 Swagger2 构建RESTful API")
				// 创建人
				.contact(new springfox.documentation.service.Contact("qjc", "https://mp.csdn.net/mdeditor/86628875#", "joecheng521@163.com"))
				// 版本号
				.version("1.0")
				// 描述
				.description("API 描述").build();
	}
}
