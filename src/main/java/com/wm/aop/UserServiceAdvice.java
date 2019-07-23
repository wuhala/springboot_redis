package com.wm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAdvice {

    private Logger logger = LoggerFactory.getLogger(UserServiceAdvice.class);

    @Pointcut("execution(public * com.wm.service..*.find*(..))")
    public void logPointcut() {
    }

    @Around(value = "logPointcut()")
    public Object serviceBefore(ProceedingJoinPoint point) throws Throwable {
        logger.info("point args", point.getArgs());
        return point.proceed();
    }
}
