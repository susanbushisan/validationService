package top.mao196.sms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 使用切面打印入参
 * @author susanbushisan
 */

@Aspect
@Component
public class LoggerAspect {

    private Logger log = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(* top.mao196.sms.controller..*.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void printParam(JoinPoint joinPoint){
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();
        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //打印请求参数
        log.info(method + ":" + Arrays.toString(args));
    }

}
