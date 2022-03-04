package com.an.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO
 *
 * @author wuya
 * @date 2022/3/4 16:10
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    private final MyInterceptor myInterceptor;

    @Autowired
    public WebConfig(MyInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        TimeFilter filter = new TimeFilter();
//        filterRegistrationBean.setFilter(filter);
//        filterRegistrationBean.addUrlPatterns("/user","/users");
//        return filterRegistrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor);
    }
}