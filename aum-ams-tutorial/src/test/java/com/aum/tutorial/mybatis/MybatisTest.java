package com.aum.tutorial.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.util.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

/**
 * @author xiayx
 */
public class MybatisTest {

    private SqlSession sqlSession;
    private User user = createUser();

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setPassword("password");
        return user;
    }

    /**
     * 1、获得 SqlSessionFactory
     * 2、获得 SqlSession
     * 3、调用在 mapper 文件中配置的 SQL 语句
     */
    @Before
    public void setUp() throws Exception {
        String resource = "com/aum/tutorial/mybatis/sqlMapConfig.xml";           // 定位核心配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);    // 创建 SqlSessionFactory
        sqlSession = sqlSessionFactory.openSession();    // 获取到 SqlSession

        Connection connection = sqlSession.getConnection();
        InputStream resourceAsStream = getClass().getResourceAsStream("user-scheme.sql");
        String sql = IOUtils.readStringAndClose(new InputStreamReader(resourceAsStream), resourceAsStream.available());
        connection.prepareStatement(sql).executeUpdate();
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
        sqlSession.clearCache();
    }


    @Test
    public void insert() throws Exception {
        int count = sqlSession.insert("com.aum.tutorial.mybatis.UserMapper.insert", user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void findAll() throws Exception {
        insert();
        // 调用 mapper 中的方法：命名空间 + id
        List<User> users = sqlSession.selectList("com.aum.tutorial.mybatis.UserMapper.findAll");
        Assert.assertEquals(1, users.size());
        User first = users.get(0);
        Assert.assertEquals(user, first);
    }

    @Test
    public void findOne() throws Exception {
        insert();
        // 调用 mapper 中的方法：命名空间 + id
        User first = sqlSession.selectOne("com.aum.tutorial.mybatis.UserMapper.findOne", user.getId());
        Assert.assertEquals(user, first);
    }

    @Test
    public void update() throws Exception {
        insert();
        int count = sqlSession.update("com.aum.tutorial.mybatis.UserMapper.update", user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void delete() throws Exception {
        insert();
        int count = sqlSession.delete("com.aum.tutorial.mybatis.UserMapper.deleteById", user.getId());
        Assert.assertEquals(1, count);
    }


}