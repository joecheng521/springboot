package com.qjc.data.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.qjc.annotation.DataSource;

/**
 * @author qjc
 * @date 2019年1月28日下午6:44:54
 * @version 1.0.0
 */
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {
	@Pointcut("@within(com.qjc.annotation.DataSource) || @annotation(com.qjc.annotation.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
    	DatabaseContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
    	DatabaseContextHolder.clearDataSource();
    }
}
