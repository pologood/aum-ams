package com.aum.platform;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * 扫描服务层
 *
 * @author xiayx
 */
@ComponentScan(
        basePackageClasses = ServiceImplLocalTestConfiguration.class,
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(Service.class)
)
public class ServiceImplLocalTestConfiguration {

}
