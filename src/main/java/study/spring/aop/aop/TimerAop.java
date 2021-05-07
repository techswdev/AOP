package study.spring.aop.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class TimerAop {

    @Pointcut("execution(* study.spring.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(study.spring.aop.annotation.Timer)")
    private void enableTimer(){}

    @Around("enableTimer()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("total time : {}", stopWatch.getTotalTimeSeconds());
    }

}
