package com.aum.platform.modules.fundaccount;

import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * tests for {@link LocalFundAccountService}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
public class FundAccountServiceImplTest {

    @Reference(version = "${demo.service.version}")
    private FundAccountService fundAccountService;

    @Test
    public void createAccount() throws Exception {
        fundAccountService.createAccount(new FundAccountVo());
    }

}