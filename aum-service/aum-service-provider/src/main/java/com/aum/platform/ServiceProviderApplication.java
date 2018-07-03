package com.aum.platform;

import com.aum.mybatis.tk.TkMybatisConfiguration;
import com.aum.spring.EnvironmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.springframework.boot.context.config.ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY;

/**
 * 服务提供者
 *
 * @author xiayx
 */
@SpringBootApplication
@Import({TkMybatisConfiguration.class})
@ImportResource("classpath:/dubbo.xml")
public class ServiceProviderApplication {

    private static Logger logger = LoggerFactory.getLogger(ServiceProviderApplication.class);

    public static void main(String[] args) {
        EnvironmentUtils.setConventionalAdditionalConfigLocation("aum-service-provider");
//        EnvironmentUtils.setAdditionalConfigLocation("classpath:template/");// 用于外部文件测试
        logger.info("{}: {}", CONFIG_LOCATION_PROPERTY, EnvironmentUtils.getConfigLocation());
        ConfigurableApplicationContext context = SpringApplication.run(ServiceProviderApplication.class, args);
//        Environment bean = context.getBean(Environment.class);
//        String property = bean.getProperty("info.app.java.source");
//        System.out.println(property);
    }


    @Bean
    public BeanFactoryPostProcessor thirdBeanFactoryPostProcessor() {
        return new ServiceAliasProcessor("pingAn", "third");
    }

    @Bean
    public BeanFactoryPostProcessor amqpBeanFactoryPostProcessor() {
        return new ServiceAliasProcessor("local", "amqp");
    }

}
