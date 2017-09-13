package com.asset.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class LoggingControllerAspects 
{
	private static Logger logger = LoggerFactory.getLogger(LoggingControllerAspects.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Pointcut("execution(* com.asset.controller.*Controller.*(..))")
	private void allControllerMethods(){}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void allRequestMappingMethods(){}
	
	@Before("allControllerMethods() && allRequestMappingMethods()")
	public void logAllControllerAndMappingMethods(JoinPoint joinPoint)
	{
		StringBuffer sb = new StringBuffer();
		try{
		Object[] objects = joinPoint.getArgs();
		for (Object object : objects)
		{
			sb.append(mapper.writeValueAsString((object))).append("|");
		}
		}catch(Exception ex)
		{
			
		}
		
		logger.info("class:{}-method:{} ###REQUEST###:{}",joinPoint.getTarget().getClass().getSimpleName(),joinPoint.getSignature().getName(),sb.toString());
	}
}
