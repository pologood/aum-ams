package com.aum.tutorial.mybatis.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.aum.tutorial.H2Test;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

/**
 * @author xiayx
 */
@ImportAutoConfiguration({
        DruidDataSourceAutoConfigure.class
})
public class DruidSpringBootTest {

    @Test
    public void basic() throws Exception {
        System.setProperty("spring.config.location", "classpath:com/aum/tutorial/mybatis/druid/application.properties");
        ConfigurableApplicationContext context = SpringApplication.run(DruidSpringBootTest.class);
        DataSource bean = context.getBean(DataSource.class);
        H2Test.basicOperate(bean.getConnection());
    }
}
