package com.aum.platform.modules.fundaccount;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 使用dubbo的代理测试异常
 *
 * @author xiayx
 */
@Service
public class FundAccountServiceStub implements FundAccountService {

    private FundAccountService fundAccountService;

    public FundAccountServiceStub(FundAccountService localFundAccountService) {
        this.fundAccountService = localFundAccountService;
    }

    @Override
    public void createAccount(FundAccountVo fundAccountVo) {
        fundAccountService.createAccount(fundAccountVo);
        throw new IllegalStateException("this exception should be throw to client");
    }

    @Override
    public List<FundAccountVo> query() {
        if (true) throw new IllegalStateException("this exception should be throw to client");
        return fundAccountService.query();
    }
}
