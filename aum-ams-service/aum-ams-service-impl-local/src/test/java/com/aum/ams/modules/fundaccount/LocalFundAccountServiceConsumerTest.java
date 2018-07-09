package com.aum.ams.modules.fundaccount;

import com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * tests for {@link FundAccountService}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboAutoConfiguration.class)
public class LocalFundAccountServiceConsumerTest {

    @Reference(group = "local")
    private FundAccountService localFundAccountService;

    @Test
    public void addAndDelete() throws Exception {
        FundAccountVo fundAccountVo = new FundAccountVo();
        fundAccountVo.setName("name");
        fundAccountVo.setPassword("password");
        Long id = localFundAccountService.add(fundAccountVo);
        //集成测试，无法自动回滚
        localFundAccountService.delete(id);
    }

    @Test
    public void query() throws Exception {
        Page<FundAccountVo> page = localFundAccountService.query(null, new PageRequest(0, 10));
        Assert.assertEquals(FundAccountTest.ALL.size(), page.getTotalElements());
    }

}