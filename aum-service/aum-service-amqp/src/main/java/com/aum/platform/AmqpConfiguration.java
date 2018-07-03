package com.aum.platform;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

/**
 * 消息队列配置
 *
 * @author xiayx
 */
public class AmqpConfiguration {

    /*--------------交换机-------------------*/
    public static final String EXCHANGE_DIRECT = "aum.direct";
    public static final String EXCHANGE_FANOUT = "aum.fanout";
    public static final String EXCHANGE_TOPIC = "aum.topic";
    public static final String EXCHANGE_HEADERS = "aum.headers";

    @Bean
    public Exchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT, true, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_FANOUT, true, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TOPIC, true, true);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(EXCHANGE_HEADERS, true, true);
    }

    /*--------------路由-------------------*/
    public static final String ROUTING_FUND_ACCOUNT_CREATE = "FundAccount.create";


}
