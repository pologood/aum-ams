package com.aum.ams.modules.fundaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Object add(FundAccountDTO fundAccountVo) {
        logger.info("创建资金账户");
        return thirdFundAccountService.add(fundAccountVo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FundAccountDTO> query(FundAccountQueryParam queryParam, Pageable pageable) {
        return localFundAccountService.query(queryParam, pageable);
    }

    @Override
    public int delete(Long... id) {
        return localFundAccountService.delete(id);
    }
}
