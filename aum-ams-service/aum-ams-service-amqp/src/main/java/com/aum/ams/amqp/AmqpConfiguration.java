package com.aum.ams.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列消费者配置
 *
 * @author xiayx
 */
@Configuration
public class AmqpConfiguration extends com.aum.ams.AmqpConfiguration {

    public static final String QUEUE_FUND_ACCOUNT_CREATE = "FundAccount.create";

    /** 资金账户创建 */
    @Bean
    public Queue fundAccountCreateQueue() {
        return new Queue(QUEUE_FUND_ACCOUNT_CREATE, true, false, false);
    }

    @Bean
    public Binding fundAccountCreateBinding() {
        return BindingBuilder.bind(fundAccountCreateQueue()).to(directExchange()).with(ROUTING_FUND_ACCOUNT_CREATE).noargs();
    }

}
