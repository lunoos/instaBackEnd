package com.insta.backend.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.insta.backend.service.*.*(..))") // This pointcut expression targets all methods in your package
    public Object logServiceMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Entering method: " + methodName);
        
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        
        logger.info("Exiting method: " + methodName);
        logger.info("Method execution time: " + (endTime - startTime) + " ms");
        
        return result;
    }
    
    @Around("@annotation(Loggable)") // This pointcut expression targets all methods in your package
    public Object logControllerMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Entering method [{}] in class [{}] with arguments {}", 
                    methodName, className, Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logger.info("Exiting method [{}] in class [{}] with result: {}",
                    methodName, className, result);
        logger.info("Method [{}] execution time: {} ms", methodName, (endTime - startTime));

        return result;
    }
}
