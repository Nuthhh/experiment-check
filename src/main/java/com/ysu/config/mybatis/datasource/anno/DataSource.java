package com.ysu.config.mybatis.datasource.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author denglinfan
 * @version 1.0
 * @createDate 2017-3-20
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)//将该注解定义在运行时级别
@Target(ElementType.METHOD)//将该注解应用于方法上
public @interface DataSource {

    String value();
}
