package com.an.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static java.lang.System.currentTimeMillis;

/**
 *
 * @Aspect:作用是把当前类标识为一个切面供容器读取
 * @Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
 * @Around：环绕增强，相当于MethodInterceptor
 * @AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 * @Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * @AfterThrowing：异常抛出增强，相当于ThrowsAdvice
 * @After: final增强，不管是抛出异常或者正常退出都会执行
 *
 * 顺序：Filter -> Interceptor -> ControllerAdvice -> Aspect -> Controller -> Aspect -> ControllerAdvice -> Interceptor -> Filter
 * ps: ControllerAdvice 是controller的增强，和ExceptionHandler一起用来做全局异常。
 *
 * 使用切面，可前置增强，后置增强，环绕增强，在拦截器之后执行，可获取参数信息
 *
 * @author wuya
 * @date 2022/3/4 16:41
 */

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.an.controller.HelloController.*(..))")
//    @After("")
//    @Before("")
//    @AfterThrowing()
//    @AfterReturning()
    public Object handlerControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("请求参数为："+arg);
        }
        System.out.println("TimeAspect 切片开始执行");
        long start = currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("切片执行耗时：" + (currentTimeMillis() - start));
        System.out.println("切片执行结束！");
        return proceed;
    }
}
