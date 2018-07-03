package com.aum.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author xiayx
 */
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class ServiceAmqpConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAmqpConsumerApplication.class, args);
    }
}
