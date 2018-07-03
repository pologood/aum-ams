package com.aum.tutorial.mybatis.spring;

import com.aum.tutorial.mybatis.druid.DruidTest;
import com.aum.tutorial.mybatis.MybatisTest;
import com.aum.tutorial.mybatis.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * @author xiayx
 */
public class MybatisSpringTest {

    @EnableTransactionManagement
    public static class Configuration {

        @Bean
        public DataSource dataSource() {
            return DruidTest.getDruidDataSource();
        }

        @Bean
        public SqlSessionFactoryBean sqlSessionFactoryBean() {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource());
            return bean;
        }

        @Bean
        public DataSourceTransactionManager dataSourceTransactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer configurer = new MapperScannerConfigurer();
            configurer.setBasePackage(MybatisSpringTest.class.getPackage().getName());
            return configurer;
        }
    }

    private User user = MybatisTest.createUser();
    private UserMapper userMapper;


    /**
     * 1、获得 SqlSessionFactory
     * 2、获得 SqlSession
     * 3、调用在 mapper 文件中配置的 SQL 语句
     */
    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        userMapper = context.getBean(UserMapper.class);

        Connection connection = context.getBean(DataSource.class).getConnection();
        ScriptUtils.executeSqlScript(connection, new InputStreamResource(getClass().getResourceAsStream("../user-scheme.sql")));
        connection.close();
    }


    @Test
    public void insert() throws Exception {
        int count = userMapper.insert(user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void findAll() throws Exception {
        insert();
        // 调用 mapper 中的方法：命名空间 + id
        List<User> users = userMapper.findAll();
        Assert.assertEquals(1, users.size());
        User first = users.get(0);
        Assert.assertEquals(user, first);
    }

    @Test
    public void findOne() throws Exception {
        insert();
        // 调用 mapper 中的方法：命名空间 + id
        User first = userMapper.findOne(user.getId());
        Assert.assertEquals(user, first);
    }

    @Test
    public void update() throws Exception {
        insert();
        int count = userMapper.update(user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void delete() throws Exception {
        insert();
        int count = userMapper.deleteById(user.getId());
        Assert.assertEquals(1, count);
    }

}
