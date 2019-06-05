package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Manager;
import com.hepangda.keshe.model.ManagerMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ManagerMapper {

    @Select("SELECT * FROM Users WHERE mid = #{id}")
    Manager getById(int id);

    @Insert("INSERT INTO Users VALUES(#{id}, #{username}, #{password}")
    boolean createUser(Manager mannager);
    @Delete("alter table Manager DROP COLUMN WHERE mid = #{clid}")
    boolean deleteManager(int id);
}
