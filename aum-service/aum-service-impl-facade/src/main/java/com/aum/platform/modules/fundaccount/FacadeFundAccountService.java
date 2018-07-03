package com.aum.platform.modules.fundaccount;

import com.aum.platform.AmqpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金账户服务的代理实现
 *
 * @author xiayx
 */
@Service
@Transactional
public class FacadeFundAccountService implements FundAccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FundAccountService localFundAccountService;
    @Autowired
    private FundAccountService thirdFundAccountService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void createAccount(FundAccountVo fundAccountVo) {
        logger.info("创建资金账户");
        thirdFundAccountService.createAccount(fundAccountVo);
        logger.info("发送【资金账户创建】消息");
        amqpTemplate.convertAndSend(AmqpConfiguration.EXCHANGE_DIRECT, AmqpConfiguration.ROUTING_FUND_ACCOUNT_CREATE, fundAccountVo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FundAccountVo> query() {
        return localFundAccountService.query();
    }
}
