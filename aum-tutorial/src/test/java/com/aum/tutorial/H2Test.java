package com.aum.tutorial;

import org.junit.Test;

import java.sql.*;

/**
 * @author xiayx
 */
public class H2Test {

    @Test
    public void basic() throws Exception {
        Class.forName("org.h2.Driver");

        System.out.println("连接h2数据库[hello]");
        String url = "jdbc:h2:mem:hello";
        Connection conn = DriverManager.getConnection(url, null, null);
        basicOperate(conn);
    }

    public static void basicOperate(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();

        System.out.println("创建表[test]");
        stat.executeUpdate("DROP TABLE IF EXISTS test");
        stat.executeUpdate("CREATE TABLE test(id INT, name VARCHAR(80))");

        System.out.println("添加数据[test]");
        stat.executeUpdate("INSERT INTO test VALUES(1, '张三')");
        stat.executeUpdate("INSERT INTO test VALUES(2, '李四')");

        System.out.println("查询数据[test]");
        ResultSet result = stat.executeQuery("SELECT * FROM test");
        while (result.next()) {
            System.out.println("id：" + result.getInt("id") + ", name：" + result.getString("name"));
        }

        //关闭数据库
        result.close();
        stat.close();
        conn.close();
    }
}