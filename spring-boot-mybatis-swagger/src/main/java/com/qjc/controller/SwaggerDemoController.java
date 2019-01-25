package com.qjc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qjc.service.IUserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author qjc
 * @date 2019年1月24日下午4:17:22
 * @version 1.0.0
 */
@Api("swaggerDemoController相关的api")
@RestController
public class SwaggerDemoController {
	private static final Logger logger = LoggerFactory.getLogger(SwaggerDemoController.class);

	@Autowired
	IUserInfoService iUserInfoService;

	@ApiOperation(value = "根据id查询用户信息", notes = "查询数据库中某个的用户信息")
	@ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long")
	@GetMapping("/user/{id}")
	public Object getUserinfo(@PathVariable Long id) {
		logger.info("开始查询某个用户信息");
		return iUserInfoService.findUserInfoById(id);
	}

}
