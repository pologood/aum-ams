package com.aum.ams.modules.fundaccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * tests for {@link PingAnFundAccountService}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PingAnFundAccountService.class)
public class PingAnFundAccountServiceTest {

    @Autowired
    private FundAccountService pingAnFundAccountService;

    @Test
    public void createAccount() throws Exception {
        pingAnFundAccountService.add(new FundAccountVo());
    }

    @Test
    public void query() throws Exception {
        pingAnFundAccountService.query(null, new PageRequest(0, 10));
    }

}