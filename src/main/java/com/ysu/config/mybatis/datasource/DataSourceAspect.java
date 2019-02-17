package com.ysu.config.mybatis.datasource;

import com.ysu.config.mybatis.datasource.anno.DataSource;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

	@Autowired
	@Qualifier("sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;

	@Pointcut("execution(* com.ysu.db.dao.*.*(..))")
     public void declareJointPointExpression() {
     }


	@Before("declareJointPointExpression()")
	public void before(JoinPoint point) {
		Object target = point.getTarget();
		String method = point.getSignature().getName();

		Class<?>[] classz = target.getClass().getInterfaces();

		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
				.getMethod().getParameterTypes();
		try {
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null) {
				if (m.isAnnotationPresent(DataSource.class)){
					DataSource data = m.getAnnotation(DataSource.class);
					com.ysu.config.mybatis.datasource.DynamicDataSourceHolder.setDataSource(data.value());
					return;
				}
				// 在没有配置的情况下走默认
				String key = classz[0].getName() + "." + m.getName();
				// 获取类型
				SqlCommandType type = sqlSessionFactory.getConfiguration()
						.getMappedStatement(key).getSqlCommandType();
				// 查询默认从库
				if (SqlCommandType.SELECT == type) {
					com.ysu.config.mybatis.datasource.DynamicDataSourceHolder.setSlave("school");
					return;
				}
				// 增删改默认主库
				if (type == SqlCommandType.DELETE
						|| SqlCommandType.INSERT == type
						|| SqlCommandType.UPDATE == type) {
					com.ysu.config.mybatis.datasource.DynamicDataSourceHolder.setMaster("school");
				}
			}

		} catch (Exception e) {

		}
	}

}