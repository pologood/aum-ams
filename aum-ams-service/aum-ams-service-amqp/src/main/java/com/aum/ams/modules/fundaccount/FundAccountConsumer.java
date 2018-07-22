package com.aum.ams.modules.fundaccount;

import com.aum.ams.amqp.AmqpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 资金账户消息队列消费者
 *
 * @author xiayx
 */
@Service
public class FundAccountConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FundAccountService amqpFundAccountService;

    @RabbitListener(queues = AmqpConfiguration.QUEUE_FUND_ACCOUNT_CREATE)
    public void createHandler(FundAccountDTO fundAccountVo) {
        logger.info("接收【资金账户创建】消息");
        amqpFundAccountService.add(fundAccountVo);
    }

}
