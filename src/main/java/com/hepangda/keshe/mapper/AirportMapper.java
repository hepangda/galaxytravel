package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Airport;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface AirportMapper {

  @Select("SELECT * FROM Airport WHERE id=#{id}")
  Airport selectById(long id);

  @Select("SELECT * FROM Airport LIMIT #{offset},#{count}")
  List<Airport> selectLimit(@Param("offset") int offset, @Param("count") int count);

  @Select("SELECT * FROM Airport")
  List<Airport> selectAll();

  @Select("SELECT * FROM Airport WHERE cityName LIKE CONCAT('%',#{keyword},'%')")
  List<Airport> selectKeyword(String keyword);

  @Select("SELECT COUNT(*) FROM Airport")
  long count();

  @Delete("DELETE FROM Airport WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Airport SET cityName=#{cityName},portName=#{portName},type=#{type} WHERE id=#{id}")
  boolean update(Airport airport);

  @Insert("INSERT INTO Airport VALUES(#{id},#{cityName},#{portName},#{type})")
  boolean insert(Airport airport);
}
