package com.aum.ams.modules.fundaccount;

import com.aum.mybatis.tk.TkPageHelperMybatisTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 * tests for {@link LocalFundAccountService}
 *
 * @author xiayx
 */
@Import(LocalFundAccountService.class)
public class LocalFundAccountServiceTest extends TkPageHelperMybatisTest {

    @Autowired
    private FundAccountService localFundAccountService;

    @Test
    public void add() throws Exception {
        FundAccountVo fundAccountVo = new FundAccountVo();
        fundAccountVo.setName("name");
        fundAccountVo.setPassword("password");
        localFundAccountService.add(fundAccountVo);
        FundAccountVo persist = localFundAccountService.get(fundAccountVo.getId());
        ReflectionAssert.assertReflectionEquals(fundAccountVo, persist);
    }

    @Test
    public void query() throws Exception {
        FundAccountQueryParam queryParam = new FundAccountQueryParam();
        queryParam.setName("刘");
        PageRequest pageable = new PageRequest(0, 2, Sort.Direction.ASC, "name");
        Page<FundAccountVo> page = localFundAccountService.query(queryParam, pageable);
        Assert.assertEquals(3, page.getTotalElements());
        Assert.assertEquals(2, page.getSize());
    }

    @Test
    public void get() throws Exception {
        FundAccountVo clone = FundAccountTest.clone(FundAccountTest.LINDAIYU, FundAccountVo.class);
        FundAccountVo persist = localFundAccountService.get(FundAccountTest.LINDAIYU.getId());
        ReflectionAssert.assertReflectionEquals(clone, persist);
    }

    @Test
    public void modify() throws Exception {
        FundAccountVo clone = FundAccountTest.clone(FundAccountTest.LINDAIYU, FundAccountVo.class);
        clone.setName("name");
        localFundAccountService.modify(clone);
        FundAccountVo persist = localFundAccountService.get(clone.getId());
        ReflectionAssert.assertReflectionEquals(clone, persist);
    }

    @Test
    public void delete() throws Exception {
        localFundAccountService.delete(FundAccountTest.LINDAIYU.getId());
        FundAccountVo persist = localFundAccountService.get(FundAccountTest.LINDAIYU.getId());
        Assert.assertNull(persist);
    }


}