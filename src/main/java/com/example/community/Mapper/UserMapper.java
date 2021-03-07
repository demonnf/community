package com.example.community.Mapper;

import com.example.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into USER (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{accountId},#{name},#{token},#{gmtcreate},#{gmtmodifie})")
     void insert(User user);
    @Select("select * from USER where TOKEN=#{token}")
    User findbytoken(@Param("token") String token);
}
