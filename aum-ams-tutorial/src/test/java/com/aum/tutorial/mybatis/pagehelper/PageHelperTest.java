package com.aum.tutorial.mybatis.pagehelper;

import com.aum.tutorial.mybatis.MybatisTest;
import com.aum.tutorial.mybatis.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.List;

/**
 * @author xiayx
 */
@ImportAutoConfiguration({
        DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        PageHelperAutoConfiguration.class
})
public class PageHelperTest {

    private User user = MybatisTest.createUser();
    private Mapper<User> userMapper;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(PageHelperTest.class.getPackage().getName());
        return configurer;
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("spring.config.location", "classpath:com/aum/tutorial/mybatis/pagehelper/application.properties");
        ConfigurableApplicationContext context = SpringApplication.run(PageHelperTest.class);
        userMapper = context.getBean(UserMapper.class);
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
        PageHelper.offsetPage(0, 10);
        List<User> users = userMapper.selectAll();
        Assert.assertTrue(users instanceof Page);
        Assert.assertEquals(1, users.size());
        User first = users.get(0);
        Assert.assertEquals(user, first);
    }

}
