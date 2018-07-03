package com.aum.platform.modules.fundaccount;

import com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * tests for {@link FundAccountService}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboAutoConfiguration.class)
public class FundAccountServiceTest {

    @Reference(group = "facade")
    private FundAccountService facadeFundAccountService;
    @Reference(group = "local")
    private FundAccountService localFundAccountService;

    @Test
    public void facadeCreateAccount() throws Exception {
        FundAccountVo fundAccountVo = new FundAccountVo();
        fundAccountVo.setName("name");
        fundAccountVo.setPassword("password");
        facadeFundAccountService.createAccount(fundAccountVo);
    }

    @Test
    public void query() throws Exception {
        List<FundAccountVo> fundAccountVos = facadeFundAccountService.query();
        System.err.println(fundAccountVos);
    }

    @Test
    public void localCreateAccount() throws Exception {
        FundAccountVo fundAccountVo = new FundAccountVo();
        fundAccountVo.setName("name");
        fundAccountVo.setPassword("password");
        localFundAccountService.createAccount(fundAccountVo);
    }

    @Test
    public void localQuery() throws Exception {
        try {
            localFundAccountService.query();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}