package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

  @Select("SELECT * FROM Users WHERE id = #{id}")
  User getById(int id);

  @Insert("INSERT INTO Users VALUES(#{id}, #{username}, #{password}")
  boolean createUser(User user);
}
