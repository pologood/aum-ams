package com.aum.platform.modules.fundaccount;

import com.aum.mybatis.tk.TkMybatisTest;
import com.aum.platform.ServiceImplLocalTestConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * tests for {@link LocalFundAccountService}
 *
 * @author xiayx
 */
@Import(ServiceImplLocalTestConfiguration.class)
public class LocalFundAccountServiceTest extends TkMybatisTest {

    @Autowired
    private FundAccountService localFundAccountService;
    private static FundAccountVo fundAccountVo = createFundAccountVo();

    private static FundAccountVo createFundAccountVo() {
        FundAccountVo record = new FundAccountVo();
        record.setName("name");
        record.setPassword("password");
        return record;
    }

    @Before
    public void setUp() throws Exception {
        createAccount();
    }

    @Test
    public void createAccount() throws Exception {
        localFundAccountService.createAccount(fundAccountVo);
    }

    @Test
    public void query() throws Exception {
        List<FundAccountVo> fundAccountVos = localFundAccountService.query();
    }
}