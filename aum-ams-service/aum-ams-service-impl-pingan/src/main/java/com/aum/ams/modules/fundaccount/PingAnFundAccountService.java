package com.aum.ams.modules.fundaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 资金账户服务的平安实现
 *
 * @author xiayx
 */
@Service
public class PingAnFundAccountService implements FundAccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Long add(FundAccountVo fundAccountVo) {
        logger.info("在平安银行创建资金账户");
        //TODO 调用平安接口
        return null;
    }

    @Override
    public Page<FundAccountVo> query(FundAccountQueryParam queryParam, Pageable pageable) {
        //TODO 平安银行未提供查询接口
        return null;
    }

}
