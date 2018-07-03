package com.aum.platform.modules.fundaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金账户服务的平安实现
 *
 * @author xiayx
 */
@Service
public class PingAnFundAccountService implements FundAccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void createAccount(FundAccountVo fundAccountVo) {
        logger.info("在平安银行创建资金账户");
        //TODO 调用平安接口
    }

    @Override
    public List<FundAccountVo> query() {
        //平安银行未提供查询接口
        throw new UnsupportedOperationException();
    }

}
