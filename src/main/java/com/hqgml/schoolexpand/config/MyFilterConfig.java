package com.hqgml.schoolexpand.config;

import com.hqgml.schoolexpand.filter.ReplaceStreamFilter;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @Description TODO
 * @Author yyf
 * @Date 2020/10/29 14:20
 * @Version 1.0
 **/
@Configuration
public class MyFilterConfig {

    @Autowired
    JwtProperties jwtProperties;


    /**
     * 注册过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(replaceStreamFilter());
        registration.addUrlPatterns("/*");
        registration.setName("replaceStreamFilter");
        return registration;
    }

    /**
     * 实例化StreamFilter
     *
     * @return Filter
     */
    @Bean(name = "replaceStreamFilter")
    public Filter replaceStreamFilter() {
        return new ReplaceStreamFilter(jwtProperties);
    }
}
