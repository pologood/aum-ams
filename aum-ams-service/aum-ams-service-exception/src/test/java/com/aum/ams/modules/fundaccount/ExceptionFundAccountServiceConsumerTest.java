package com.aum.ams.modules.fundaccount;

import com.github.peacetrue.util.AssertUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ExceptionFundAccountService.class
})
public class ExceptionFundAccountServiceConsumerTest {

    @Autowired
    private FundAccountService exceptionFundAccountService;

    @Test
    public void add() throws Exception {
        AssertUtils.assertException(() -> {
            exceptionFundAccountService.add(new FundAccountDTO());
        });
    }

    @Test
    public void query() throws Exception {
        AssertUtils.assertException(() -> {
            exceptionFundAccountService.query(new FundAccountQueryParam(), new PageRequest(0, 10));
        });
    }

}