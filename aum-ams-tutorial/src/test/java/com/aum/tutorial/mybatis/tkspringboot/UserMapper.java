package com.aum.tutorial.mybatis.tkspringboot;

import com.aum.tutorial.mybatis.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiayx
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
}
