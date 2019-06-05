package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ClientMapper {

  @Select("SELECT * FROM Users WHERE clid = #{clid}")
  Client getById(int id);

  @Insert("INSERT INTO Users VALUES(#{clid},#{idnumber}, #{name},#{gener}, #{password},#{age},#{vip})")
  boolean createUser(Client client);

  @Delete("alter table Client DROP COLUMN WHERE clid = #{clid}")
  boolean deleteClient(int id);
}
