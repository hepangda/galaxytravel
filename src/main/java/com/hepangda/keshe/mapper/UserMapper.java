package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

  @Select("SELECT * FROM User WHERE id=#{id}")
  User selectById(long id);

  @Select("SELECT * FROM User WHERE username=#{username}")
  User selectByUsername(String username);

  @Select("SELECT * FROM User LIMIT #{offset},#{count}")
  List<User> selectLimit(int offset, int count);

  @Delete("DELETE FROM User WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE User SET username=#{username},pwd=#{pwd},type=#{type},"
      + "realname=#{realname},phone=#{phone} WHERE id=#{id}")
  boolean update(User user);

  @Insert("INSERT INTO User VALUES(#{id},#{username},#{pwd},#{type},#{realname},#{phone})")
  boolean insert(User user);
}
