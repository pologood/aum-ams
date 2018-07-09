package com.aum.tutorial.mybatis.springboot;

import com.aum.tutorial.mybatis.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xiayx
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    User findOne(Long id);

    @Insert("insert into user (id, name, password) values (#{id}, #{name}, #{password})")
    int insert(User user);

    @Update("update user set name=#{name},password=#{password} where id = #{id}")
    int update(User user);

    @Delete("delete from user where id = #{id}")
    int deleteById(Long id);
}
