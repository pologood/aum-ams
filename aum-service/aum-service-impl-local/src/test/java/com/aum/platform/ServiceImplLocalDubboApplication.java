package com.aum.platform;

import com.aum.mybatis.tk.TkMybatisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * dubbo服务
 *
 * @author xiayx
 */
@SpringBootApplication
@Import({TkMybatisConfiguration.class})
@ImportResource("classpath:/dubbo.xml")
public class ServiceImplLocalDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceImplLocalDubboApplication.class, args);
    }
}
