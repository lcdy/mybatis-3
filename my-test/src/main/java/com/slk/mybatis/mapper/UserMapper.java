package com.slk.mybatis.mapper;


import com.slk.mybatis.domain.User;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {


  @Select("select * from user where id = 10")
  User selectById();

}
