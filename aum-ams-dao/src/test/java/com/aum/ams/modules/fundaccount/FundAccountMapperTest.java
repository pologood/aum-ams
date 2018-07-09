package com.aum.ams.modules.fundaccount;

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

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * tests for {@link FundAccountMapper}
 *
 * @author xiayx
 */
public class FundAccountMapperTest extends TkPageHelperMybatisTest {

    public Example LINDAIYU_EXAMPLE;

    private static Example createExample(FundAccount fundAccount) {
        Example example = new Example(FundAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", fundAccount.getName());
        criteria.andEqualTo("password", fundAccount.getPassword());
        return example;
    }

    @Before
    public void setUp() throws Exception {
        LINDAIYU_EXAMPLE = createExample(FundAccountTest.LINDAIYU);
    }

    @Autowired
    private FundAccountMapper fundAccountMapper;

    @Test
    public void insert() throws Exception {
        FundAccount fundAccount = new FundAccount();
        fundAccount.setName("name");
        fundAccount.setPassword("password");
        int i = fundAccountMapper.insert(fundAccount);
        Assert.assertEquals(1, i);

        FundAccount persist = fundAccountMapper.selectByPrimaryKey(fundAccount.getId());
        assertReflectionEquals(fundAccount, persist);
    }

    @Test
    public void insertSelective() throws Exception {
        FundAccount fundAccount = new FundAccount();
        fundAccount.setName("name");
        fundAccount.setPassword("password");
        int i = fundAccountMapper.insertSelective(fundAccount);
        Assert.assertEquals(1, i);

        FundAccount persist = fundAccountMapper.selectByPrimaryKey(fundAccount.getId());
        assertReflectionEquals(fundAccount, persist);
    }

    @Test
    public void selectAll() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectAll();
        assertReflectionEquals(FundAccountTest.ALL, fundAccounts);
    }

    @Test
    public void select() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.select(FundAccountTest.LINDAIYU);
        Assert.assertEquals(1, fundAccounts.size());
        assertReflectionEquals(FundAccountTest.LINDAIYU, fundAccounts.get(0));
    }

    @Test
    public void selectByExample() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectByExample(LINDAIYU_EXAMPLE);
        Assert.assertEquals(1, fundAccounts.size());
        assertReflectionEquals(FundAccountTest.LINDAIYU, fundAccounts.get(0));
    }

    @Test
    public void selectByExampleAndRowBounds() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectByExampleAndRowBounds(LINDAIYU_EXAMPLE, new RowBounds(0, 10));
        Assert.assertEquals(1, fundAccounts.size());

        fundAccounts = fundAccountMapper.selectByExampleAndRowBounds(LINDAIYU_EXAMPLE, new RowBounds(1, 1));
        Assert.assertEquals(0, fundAccounts.size());
    }

    @Test
    public void selectByRowBounds() throws Exception {
        List<FundAccount> fundAccounts = fundAccountMapper.selectByRowBounds(FundAccountTest.LINDAIYU, new RowBounds(0, 1));
        Assert.assertEquals(1, fundAccounts.size());

        fundAccounts = fundAccountMapper.selectByRowBounds(FundAccountTest.LINDAIYU, new RowBounds(1, 1));
        Assert.assertEquals(0, fundAccounts.size());
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(FundAccountTest.LINDAIYU.getId());
        assertReflectionEquals(FundAccountTest.LINDAIYU, fundAccount);
    }

    @Test
    public void selectOne() throws Exception {
        FundAccount fundAccount = fundAccountMapper.selectOne(FundAccountTest.LINDAIYU);
        assertReflectionEquals(FundAccountTest.LINDAIYU, fundAccount);
    }

    @Test
    public void selectOneByExample() throws Exception {
        FundAccount fundAccount = fundAccountMapper.selectOneByExample(LINDAIYU_EXAMPLE);
        assertReflectionEquals(FundAccountTest.LINDAIYU, fundAccount);
    }

    @Test
    public void selectCount() throws Exception {
        int i = fundAccountMapper.selectCount(FundAccountTest.LINDAIYU);
        Assert.assertEquals(1, i);
    }

    @Test
    public void selectCountByExample() throws Exception {
        int i = fundAccountMapper.selectCountByExample(LINDAIYU_EXAMPLE);
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
        FundAccount clone = FundAccountTest.clone(FundAccountTest.LINDAIYU);
        clone.setName("name");
        clone.setPassword(null);
        int i = fundAccountMapper.updateByPrimaryKey(clone);
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(clone.getId());
        clone.setPassword("");
        assertReflectionEquals(clone, persist);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        FundAccount clone = FundAccountTest.clone(FundAccountTest.LINDAIYU);
        clone.setName("name");
        clone.setPassword(null);
        int i = fundAccountMapper.updateByPrimaryKeySelective(clone);
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(clone.getId());
        Assert.assertEquals(clone.getName(), persist.getName());
        Assert.assertEquals(FundAccountTest.LINDAIYU.getPassword(), persist.getPassword());
    }

    @Test
    public void updateByExample() throws Exception {
        FundAccount clone = FundAccountTest.clone(FundAccountTest.LINDAIYU);
        clone.setName("name");
        clone.setPassword(null);
        int i = fundAccountMapper.updateByExample(clone, LINDAIYU_EXAMPLE);
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(clone.getId());
        clone.setPassword("");//password not allow null, null -> ""
        assertReflectionEquals(clone, persist);
    }

    @Test
    public void updateByExampleSelective() throws Exception {
        FundAccount clone = FundAccountTest.clone(FundAccountTest.LINDAIYU);
        clone.setName("name");
        clone.setPassword(null);
        int i = fundAccountMapper.updateByExampleSelective(clone, LINDAIYU_EXAMPLE);
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(clone.getId());
        Assert.assertEquals(clone.getName(), persist.getName());
        Assert.assertEquals(FundAccountTest.LINDAIYU.getPassword(), persist.getPassword());
    }

    @Test
    public void delete() throws Exception {
        int i = fundAccountMapper.delete(FundAccountTest.LINDAIYU);
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(FundAccountTest.LINDAIYU.getId());
        Assert.assertNull(persist);
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {
        int i = fundAccountMapper.deleteByPrimaryKey(FundAccountTest.LINDAIYU.getId());
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(FundAccountTest.LINDAIYU.getId());
        Assert.assertNull(persist);
    }

    @Test
    public void deleteByExample() throws Exception {
        int i = fundAccountMapper.deleteByExample(LINDAIYU_EXAMPLE);
        Assert.assertEquals(1, i);
        FundAccount persist = fundAccountMapper.selectByPrimaryKey(FundAccountTest.LINDAIYU.getId());
        Assert.assertNull(persist);
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