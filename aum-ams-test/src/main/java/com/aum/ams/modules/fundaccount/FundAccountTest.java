package com.aum.ams.modules.fundaccount;

import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 资金账户测试数据
 *
 * @author xiayx
 * @see data.sql
 */
public class FundAccountTest {

    private static long id = 1L;
    public static final FundAccount LINDAIYU = create("林黛玉", "password");
    public static final FundAccount JIABAOYU = create("贾宝玉", "password");
    public static final FundAccount LIUYIFEI = create("刘亦菲", "password");
    public static final FundAccount LIUBOWEN = create("刘伯温", "password");
    public static final FundAccount LIUTIANCI = create("刘天赐", "password");
    public static final List<FundAccount> ALL = Arrays.asList(LINDAIYU, JIABAOYU, LIUYIFEI, LIUBOWEN, LIUTIANCI);

    public static FundAccount create(String name, String password) {
        return create(id++, name, password);
    }

    public static FundAccount create(Long id, String name, String password) {
        FundAccount fundAccount = new FundAccount();
        fundAccount.setId(id);
        fundAccount.setName(name);
        fundAccount.setPassword(password);
        return fundAccount;
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T source) {
        return clone(source, (Class<T>) source.getClass());
    }

    public static <T> T clone(Object source, Class<T> targetClass) {
        T clone = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, clone);
        return clone;
    }
}
