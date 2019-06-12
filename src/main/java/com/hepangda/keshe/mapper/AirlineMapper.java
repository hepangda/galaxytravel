package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Airline;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface AirlineMapper {

  @Select("SELECT * FROM Airline WHERE id=#{id}")
  Airline selectById(long id);

  @Select("SELECT * FROM Airline LIMIT #{offset},#{count}")
  List<Airline> selectLimit(int offset, int count);

  @Select("SELECT * FROM Airline")
  List<Airline> selectAll();

  @Delete("DELETE FROM Airline WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Airline SET name=#{name},sourcePortId=#{sourcePortId},"
      + "destPortId=#{destPortId},type=#{type} WHERE id=#{id}")
  boolean update(Airline airline);

  @Insert("INSERT INTO Airline VALUES(#{id}, #{name},#{sourcePortId},#{destPortId},#{type})")
  boolean insert(Airline airline);
}
