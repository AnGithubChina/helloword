package com.an.controller;

import com.an.config.DatabaseConfig;
import com.an.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author wuya
 * @date 2022/3/4 14:30
 */

@RestController
public class HelloController {

    @Resource
    private DatabaseConfig databaseConfig;

    @GetMapping("sayHi")
    public String sayHi() {
        return "Welcome to Springboot!";
    }

    @GetMapping("getAppConfig")
    public String getAppConfig() {

        return "Oracle:" + databaseConfig.getOracle() + "Mysql:" + databaseConfig.getMysql();

    }

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter filter = new TimeFilter();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
