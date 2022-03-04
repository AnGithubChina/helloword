package com.an.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器是服务端的一个组件，是基于servlet实现从客户端访问服务端web资源的一种拦截机制，对请求request和响应response都进行过滤，依赖于serverlet容器，使用时，实现Filter接口，在web.xml里配置对应的class还有mapping-url，springboot工程可以通FilterRegisteration配置后,设置要过滤的URL， 注意 两种方式过滤器都是有序的，谁在前就先调用谁！定义过滤器后会重写三个方法，分别是init(),doFilter(),和destory(),
 *
 * 过滤器拦截web访问url地址。 严格意义上讲，filter只是适用于web中，依赖于Servlet容器，利用Java的回调机制进行实现。
 * Filter过滤器：和框架无关，可以控制最初的http请求，但是更细一点的类和方法控制不了。
 * 过滤器可以拦截到方法的请求和响应(ServletRequest request, ServletResponse response)，并对请求响应做出像响应的过滤操作，
 * 比如设置字符编码，鉴权操作等
 *
 *用法：实现Filter接口，或继承其实现类
 * (eg: GenericFilterBean -> OncePerRequestFilter -> AbstractRequestLoggingFilter)
 * @author wuya
 * @date 2022/3/4 15:21
 */

//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行了");
        long start2 = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long time = System.currentTimeMillis() - start2;
        System.out.println("过滤器执行的时间是 ：" + time);
        System.out.println("过滤器执行结束");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁了");
    }


//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        TimeFilter filter = new TimeFilter();
//        filterRegistrationBean.setFilter(filter);
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }
}
