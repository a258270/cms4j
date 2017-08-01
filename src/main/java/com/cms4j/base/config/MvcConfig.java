package com.cms4j.base.config;

import com.cms4j.base.interceptor.LoginInterceptor;
import com.cms4j.base.interceptor.VersionInterceptor;
import com.cms4j.base.plugin.BaseSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * description: Springmvc配置
 *
 * @author: zmj
 * @create: 2017/5/7
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private BaseSetting baseSetting;
    @Value("${spring.profiles.active}")
    private String active;
    @Value("${spring.profiles.version}")
    private String version;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**/**");
        registry.addWebRequestInterceptor(new VersionInterceptor(baseSetting, active, version));
        super.addInterceptors(registry);
    }
}