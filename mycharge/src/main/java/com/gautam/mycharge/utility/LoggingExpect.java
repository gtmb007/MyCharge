package com.gautam.mycharge.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingExpect {

	private Logger logger=LogManager.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "execution(* com.gautam.mycharge.dao.*Impl.*(..))", throwing = "exception")	
	public void logExceptionFromDAO(Exception exception) throws Exception {
		logger.error(exception.getMessage(), exception);
	}


	@AfterThrowing(pointcut = "execution(* com.gautam.mycharge.service.*Impl.*(..))", throwing = "exception")	
	public void logExceptionFromService(Exception exception) throws Exception {
		logger.error(exception.getMessage(), exception);
	}
}
