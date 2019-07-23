package com.wm;

import com.wm.Interceptot.MyInterceptor;
import com.wm.Interceptot.MyInterceptor2;
import com.wm.filter.MyFilter;
import com.wm.listener.MyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ServletComponentScan
public class Config extends WebMvcConfigurationSupport {
    @Bean
    public MyInterceptor initInterceptor() {
        return  new MyInterceptor();
    }
    @Bean
    public MyInterceptor2 initInterceptor2() {
        return  new MyInterceptor2();
    }
    //增加拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(initInterceptor2()).addPathPatterns("/**").order(2);
        registry.addInterceptor(initInterceptor())//指定拦截器类
            .addPathPatterns("/**").order(1);    //指定该类拦截的url
    }

    //过滤器
    @Bean
    public FilterRegistrationBean<MyFilter> authFilter(@Autowired MyFilter myFilter) {
        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>(myFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    //监听器
    @Bean
    public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean<MyListener>(new MyListener());
    }
}
