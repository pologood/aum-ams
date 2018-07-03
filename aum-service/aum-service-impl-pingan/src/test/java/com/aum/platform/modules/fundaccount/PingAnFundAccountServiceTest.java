package com.aum.platform.modules.fundaccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * tests for {@link PingAnFundAccountService}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PingAnFundAccountServiceTest.class)
@ComponentScan(basePackageClasses = PingAnFundAccountServiceTest.class)
public class PingAnFundAccountServiceTest {

    @Autowired
    private FundAccountService pingAnFundAccountService;

    @Test
    public void createAccount() throws Exception {
        pingAnFundAccountService.createAccount(new FundAccountVo());
    }

    @Test
    public void query() throws Exception {
//        pingAnFundAccountService.query();
    }

}