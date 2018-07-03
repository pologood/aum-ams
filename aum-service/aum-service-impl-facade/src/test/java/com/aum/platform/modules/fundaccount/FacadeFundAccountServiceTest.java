package com.aum.platform.modules.fundaccount;

import com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration;
import com.aum.platform.ServiceImplFacadeTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ServiceImplFacadeTestConfiguration.class,
        RabbitAutoConfiguration.class,
        DubboAutoConfiguration.class
})
@EnableAspectJAutoProxy
public class FacadeFundAccountServiceTest {

    @Autowired
    private FundAccountService facadeFundAccountService;

    @Test
    public void createAccount() throws Exception {
        facadeFundAccountService.createAccount(new FundAccountVo());
    }

    @Test
    public void query() throws Exception {
        facadeFundAccountService.query();
    }

}