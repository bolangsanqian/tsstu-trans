package com.tsstu.front.core.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(value=1)
@Component
public class DataSourceContextAop {

    @Around("execution(@com.tsstu.front.core.datasource.DataSource * com.tsstu.front.service.impl.*.*(..))")  
	public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
		Method method = this.getMethod(pjp);
		DataSource dataSource = method.getAnnotation(DataSource.class);
		if (dataSource != null) {
			DBContextHolder.switchDataSource(dataSource);
		}
		return pjp.proceed();
	}
	
	public Method getMethod(JoinPoint pjp) {
		Method method = null;
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		method = signature.getMethod();
		return method;
	}

}
