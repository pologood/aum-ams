package com.aum.permission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 权限配置
 *
 * @author xiayx
 */
@Configuration
public class PermissionConfiguration {

    @Bean
    public PermissionsInterceptor permissionsInterceptor() {
        return new PermissionsInterceptor();
    }

    @Bean
    public PermissionController permissionController() {
        return new PermissionController();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("index.html").setViewName("index");
                registry.addViewController("main.html").setViewName("main");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(permissionsInterceptor());
            }
        };
    }

}
