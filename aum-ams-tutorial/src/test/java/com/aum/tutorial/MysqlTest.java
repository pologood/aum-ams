package com.aum.tutorial;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author xiayx
 */
public class MysqlTest {

    @Test
    public void basic() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("连接mysql数据库[未指定具体数据库]");
        String url = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stat = conn.createStatement();
        System.out.println("创建数据库[hello]");
        stat.executeUpdate("CREATE DATABASE hello");
        stat.close();
        conn.close();

        System.out.println("连接mysql数据库[hello]");
        url = "jdbc:mysql://localhost:3306/hello?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        conn = DriverManager.getConnection(url, "root", "root");
        stat = conn.createStatement();

        System.out.println("创建表[test]");
        stat.executeUpdate("CREATE TABLE test(id INT, name VARCHAR(80))");

        System.out.println("添加数据[test]");
        stat.executeUpdate("INSERT INTO test VALUES(1, '张三')");
        stat.executeUpdate("INSERT INTO test VALUES(2, '李四')");

        System.out.println("查询数据[test]");
        ResultSet result = stat.executeQuery("SELECT * FROM test");
        while (result.next()) {
            System.out.println("id：" + result.getInt("id") + ", name：" + result.getString("name"));
        }

        System.out.println("删除数据库[hello]");
        stat.executeUpdate("DROP DATABASE hello");

        //关闭数据库
        result.close();
        stat.close();
        conn.close();
    }
}