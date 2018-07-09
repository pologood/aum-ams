package com.aum.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * dubbo服务
 *
 * @author xiayx
 */
@SpringBootApplication
@ImportResource("classpath:/dubbo.xml")
public class ServiceImplPingAnProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceImplPingAnProviderApplication.class, args);
    }
}
