package com.aum.ams.synchronize;

import com.aum.ams.AmqpConfiguration;
import com.aum.ams.modules.fundaccount.FundAccountVo;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 同步切面，通过消息队列发送通知
 *
 * @author xiayx
 */
@Aspect
@Service
public class FundAccountAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Pointcut("execution(* com.aum.ams.modules.fundaccount.FacadeFundAccountService.add(..))&&args(fundAccountVo)")
    public void fundAccount_create_pointcut(FundAccountVo fundAccountVo) {}

    @After("fundAccount_create_pointcut(fundAccountVo)")
    public void fundAccount_create_advice(FundAccountVo fundAccountVo) throws Throwable {
        logger.info("发送【资金账户创建】消息");
        amqpTemplate.convertAndSend(AmqpConfiguration.EXCHANGE_DIRECT, AmqpConfiguration.ROUTING_FUND_ACCOUNT_CREATE, fundAccountVo);
    }

}