package com.aum.mybatis.tk;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 避免重复定义 {@link tk.mybatis.spring.annotation.MapperScan}
 *
 * @author xiayx
 */
public class TkMybatisConfiguration {

    public static final String BASE_PACKAGE = "mybatis.base-package";

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(environment.getRequiredProperty(BASE_PACKAGE));
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }
}
