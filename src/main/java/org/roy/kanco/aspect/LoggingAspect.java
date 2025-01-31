package org.roy.kanco.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* org.roy.kanco.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing method "+joinPoint.getSignature().getName());
    }
}
