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
        FundAccountDTO fundAccountVo = new FundAccountDTO();
        fundAccountVo.setName("name");
        fundAccountVo.setPassword("password");
        Long id = (Long) localFundAccountService.add(fundAccountVo);
        FundAccountDTO persist = localFundAccountService.get(id);
        ReflectionAssert.assertReflectionEquals(fundAccountVo, persist);
    }

    @Test
    public void query() throws Exception {
        FundAccountQueryParam queryParam = new FundAccountQueryParam();
        queryParam.setName("刘");
        PageRequest pageable = new PageRequest(0, 2, Sort.Direction.ASC, "name");
        Page<FundAccountDTO> page = localFundAccountService.query(queryParam, pageable);
        Assert.assertEquals(3, page.getTotalElements());
        Assert.assertEquals(2, page.getSize());
    }

    @Test
    public void get() throws Exception {
        FundAccountDTO clone = FundAccountTest.clone(FundAccountTest.LINDAIYU, FundAccountDTO.class);
        FundAccountDTO persist = localFundAccountService.get(FundAccountTest.LINDAIYU.getId());
        ReflectionAssert.assertReflectionEquals(clone, persist);
    }

    @Test
    public void modify() throws Exception {
        FundAccountDTO clone = FundAccountTest.clone(FundAccountTest.LINDAIYU, FundAccountDTO.class);
        clone.setName("name");
        localFundAccountService.modify(clone);
//        FundAccountDTO persist = localFundAccountService.get(clone.getId());
//        ReflectionAssert.assertReflectionEquals(clone, persist);
    }

    @Test
    public void delete() throws Exception {
        localFundAccountService.delete(FundAccountTest.LINDAIYU.getId());
        FundAccountDTO persist = localFundAccountService.get(FundAccountTest.LINDAIYU.getId());
        Assert.assertNull(persist);
    }


}