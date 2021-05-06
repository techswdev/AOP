package study.spring.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class CustomAop {

    @Pointcut("execution(* study.spring.aop.controller..*.*(..))")
    private void cut(){}

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("----------before start---------------------");
        log.info("method name : {}",method.getName());

        Object [] args = joinPoint.getArgs();
        for(Object obj : args){

            log.info("obj.getClass().getSimpleName() : {}", obj.getClass().getSimpleName());
            log.info("obj : {}", obj);
        }

        log.info("----------before end---------------------");

    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){

        log.info("----------after start---------------------");

        log.info("joinPoint : {}", joinPoint);
        log.info("returnObj : {}", returnObj);

        log.info("----------after end---------------------");

    }
}
