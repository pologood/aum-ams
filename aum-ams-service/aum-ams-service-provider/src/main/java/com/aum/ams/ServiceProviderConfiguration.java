package com.aum.ams;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 服务提供者配置
 *
 * @author xiayx
 */
@Configuration
public class ServiceProviderConfiguration {

    @Bean
    public BeanFactoryPostProcessor thirdBeanFactoryPostProcessor() {
        return new ServiceAliasProcessor("pingAn", "third");
    }

    @Bean
    public BeanFactoryPostProcessor amqpBeanFactoryPostProcessor() {
        return new ServiceAliasProcessor("local", "amqp");
    }

}
