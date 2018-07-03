package com.aum.tutorial.mybatis.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.aum.tutorial.H2Test;
import org.junit.Test;

/**
 * @author xiayx
 * see http://druid.io/
 */
public class DruidTest {

    @Test
    public void basic() throws Exception {
        DruidDataSource dataSource = getDruidDataSource();
        H2Test.basicOperate(dataSource.getConnection());
    }

    public static DruidDataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:hello");
        return dataSource;
    }
}
