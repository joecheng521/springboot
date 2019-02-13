package com.qjc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qjc.enums.DataSourceEnum;

/**
 * @author qjc
 * @date 2019年1月28日下午3:27:20
 * @version 1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	 DataSourceEnum value() default DataSourceEnum.DB1;
}
