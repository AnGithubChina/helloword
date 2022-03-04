package com.an.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.currentTimeMillis;

/**
 * 拦截器拦截以 .action结尾的url，拦截Action的访问。 Interfactor是基于Java的反射机制（APO思想）进行实现，不依赖Servlet容器。
 * 拦截器可以在方法执行之前(preHandle)和方法执行之后(afterCompletion)进行操作，回调操作(postHandle)，可以获取执行的方法的名称，请求(HttpServletRequest)
 * Interceptor：可以控制请求的控制器和方法，但控制不了请求方法里的参数(只能获取参数的名称，不能获取到参数的值)
 * （用于处理页面提交的请求响应并进行处理，例如做国际化，做主题更换，过滤等）。
 *
 * 拦截器，顾名思义，他的作用就是拦截，这个要和过滤器区分开，过滤器依赖serverlet容器，获取request和response处理，是基于函数回调，简单说就是“去取你想取的”，拦截器是通过java反射机制，动态代理来拦截web请求，是“拒你想拒绝的”，他只拦截web请求，但不拦截静态资源，Struts2里面就是将拦截器串联，实现对请求的处理
 *
 *
 *用法 步骤1：继承HandlerInterceptorAdapter（是HandlerInterceptor接口的实现类，有默认的空实现，可以自由选择自己需要的method去实现）
 *
 * @author wuya
 * @date 2022/3/4 15:00
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object hanlder) {
       System.out.println("拦截器.preHandle 开始执行。。。");
        System.out.println(hanlder.getClass().getSimpleName());
        System.out.println(((HandlerMethod) hanlder).getBean().getClass().getName());
        httpServletRequest.setAttribute("start", currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object hanlder, ModelAndView modelAndView) {
        System.out.println("拦截器.postHandle 开始执行。。。");
        long start = (long) httpServletRequest.getAttribute("start");
        System.out.println("postHandle执行时间为：" + (currentTimeMillis() - start));

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object hanlder, Exception e) {
        //会打印两次 spring里面的basic error 也会被拦截
        System.out.println("拦截器.afterCompletion 开始执行。。。");
        long start = (long) httpServletRequest.getAttribute("start");
        System.out.println("afterCompletion执行时间为：" + (currentTimeMillis() - start));
        System.out.println("\n ex is :" + e+"\n");
    }
}
