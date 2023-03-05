package com.chunlei.auth.dao;

import com.chunlei.auth.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {
    @Select("select name as username, password from user where name = #{username}")
    User findByName(String username);
}
