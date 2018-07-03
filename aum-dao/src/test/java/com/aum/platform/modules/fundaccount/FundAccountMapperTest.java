package com.aum.platform.modules.fundaccount;

import com.aum.mybatis.tk.TkPageHelperMybatisTest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * tests for {@link FundAccountMapper}
 *
 * @author xiayx
 */
public class FundAccountMapperTest extends TkPageHelperMybatisTest {

    @Autowired
    private FundAccountMapper fundAccountMapper;
    static FundAccount fundAccount = createFundAccount();

    static FundAccount createFundAccount() {
        FundAccount record = new FundAccount();
        record.setName("name");
        record.setPassword("password");
        return record;
    }

    private static Example createExample() {
        Example example = new Example(FundAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", fundAccount.getName());
        criteria.andEqualTo("password", fundAccount.getPassword());
        return example;
    }

    @Before
    public void setUp() throws Exception {
        fundAccountMapper.insert(fundAccount);
    }

    @Test
    public void selectAll() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectAll();
        Assert.assertEquals(1, fundAccounts.size());
    }

    @Test
    public void select() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.select(createFundAccount());
        Assert.assertEquals(1, fundAccounts.size());
    }

    @Test
    public void selectByExample() throws Exception {
        Example example = createExample();
        List<FundAccount> fundAccounts = fundAccountMapper.selectByExample(example);
        Assert.assertEquals(1, fundAccounts.size());
    }

    @Test
    public void selectByExampleAndRowBounds() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectByExampleAndRowBounds(createExample(), new RowBounds(0, 10));
        Assert.assertEquals(1, fundAccounts.size());

        fundAccounts = fundAccountMapper.selectByExampleAndRowBounds(createExample(), new RowBounds(0, 0));
        Assert.assertEquals(0, fundAccounts.size());
    }

    @Test
    public void selectByRowBounds() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectByRowBounds(fundAccount, new RowBounds(0, 1));
        Assert.assertEquals(1, fundAccounts.size());

        fundAccounts = fundAccountMapper.selectByRowBounds(fundAccount, new RowBounds(0, 0));
        Assert.assertEquals(0, fundAccounts.size());
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(FundAccountMapperTest.fundAccount.getId());
        Assert.assertEquals(FundAccountMapperTest.fundAccount.getId(), fundAccount.getId());
    }

    @Test
    public void selectOne() throws Exception {
        FundAccount fundAccount = fundAccountMapper.selectOne(FundAccountMapperTest.fundAccount);
        Assert.assertEquals(FundAccountMapperTest.fundAccount.getId(), fundAccount.getId());
    }

    @Test
    public void selectOneByExample() throws Exception {
        FundAccount fundAccount = fundAccountMapper.selectOneByExample(createExample());
        Assert.assertEquals(FundAccountMapperTest.fundAccount.getId(), fundAccount.getId());
    }

    @Test
    public void selectCount() throws Exception {
        int i = fundAccountMapper.selectCount(fundAccount);
        Assert.assertEquals(1, i);
    }

    @Test
    public void selectCountByExample() throws Exception {
        int i = fundAccountMapper.selectCountByExample(createExample());
        Assert.assertEquals(1, i);
    }

    @Test
    public void insertSelective() throws Exception {
        int i = fundAccountMapper.insertSelective(createFundAccount());
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
        int i = fundAccountMapper.updateByPrimaryKey(fundAccount);
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        int i = fundAccountMapper.updateByPrimaryKeySelective(fundAccount);
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateByExample() throws Exception {
        int i = fundAccountMapper.updateByExample(fundAccount, createExample());
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateByExampleSelective() throws Exception {
        int i = fundAccountMapper.updateByExampleSelective(fundAccount, createExample());
        Assert.assertEquals(1, i);
    }

    @Test
    public void delete() throws Exception {
        int i = fundAccountMapper.delete(fundAccount);
        Assert.assertEquals(1, i);
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {
        int i = fundAccountMapper.deleteByPrimaryKey(fundAccount.getId());
        Assert.assertEquals(1, i);
    }

    @Test
    public void deleteByExample() throws Exception {
        int i = fundAccountMapper.deleteByExample(createExample());
        Assert.assertEquals(1, i);
    }


    @Test
    public void selectByPage() throws Exception {
        RowBounds rowBounds = new RowBounds(0, 1);
        PageHelper.offsetPage(rowBounds.getOffset(), rowBounds.getLimit());
        List<FundAccount> fundAccounts = fundAccountMapper.selectAll();
        Assert.assertTrue(fundAccounts instanceof Page);
        Assert.assertEquals(1, fundAccounts.size());
    }

}