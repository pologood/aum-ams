package com.aum.mybatis.tk;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

/**
 * tests for tk.mybatis
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                DataSourceAutoConfiguration.class,
                MapperAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                TransactionAutoConfiguration.class,
                TkMybatisConfiguration.class,
        }
//        properties = {
//                "spring.config.name=application-mybatis",//禁用所有默认配置，并使用自定义配置
//        }
)
@Transactional
public abstract class TkMybatisTest {}