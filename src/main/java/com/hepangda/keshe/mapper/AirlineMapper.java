package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Airline;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface AirlineMapper {

  @Select("SELECT * FROM Airline WHERE id=#{id}")
  Airline selectById(long id);

  @Delete("DELETE FROM Airline WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Airline SET name=#{name},sourcePortId=#{sourcePortId},"
      + "destPortId=#{destPortId},type=#{type} WHERE id=#{id}")
  boolean update(Airline airline);

  @Insert("INSERT INTO Airline VALUES(#{id}, #{name},#{sourcePortId},#{destPortId},#{type})")
  boolean insert(Airline airline);
}
