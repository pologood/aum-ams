package com.aum.ams.modules.fundaccount;

import com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration;
import com.alibaba.dubbo.config.annotation.Reference;
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
public class FacadeFundAccountServiceConsumerTest {

    @Reference(group = "facade")
    private FundAccountService facadeFundAccountService;

    @Test
    public void add() throws Exception {
        FundAccountDTO fundAccountVo = new FundAccountDTO();
        fundAccountVo.setName("name");
        fundAccountVo.setPassword("facade");
        Object id = facadeFundAccountService.add(fundAccountVo);
        facadeFundAccountService.delete((Long) id);
    }

    @Test
    public void query() throws Exception {
        Page<FundAccountDTO> fundAccountVos = facadeFundAccountService.query(
                new FundAccountQueryParam(),
                new PageRequest(0, 1)
        );

    }


}