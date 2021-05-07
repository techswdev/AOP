package study.spring.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import study.spring.aop.dto.User;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
@Slf4j
public class DecodeAop {


    @Pointcut("execution(* study.spring.aop.controller..*.*(..))")
    private void cut() {
    }

    @Pointcut("@annotation(study.spring.aop.annotation.Decode)")
    private void enableDecode() {
    }


    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof User) {
                User user = User.class.cast(obj);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                user.setEmail(email);
            }
        }
    }


    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj) {
        if (returnObj instanceof User) {
            User user = User.class.cast(returnObj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
            user.setEmail(base64Email);
        }
    }

}
