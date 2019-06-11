package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Flight;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface FlightMapper {

  @Select("SELECT * FROM Flight WHERE id=#{id}")
  Flight selectById(long id);

  @Select("SELECT * FROM Flight LIMIT #{offset},#{count}")
  List<Flight> selectLimit(int offset, int count);

  @Delete("DELETE FROM Flight WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Flight SET airlineId=#{airlineId},airplaneId=#{airplaneId},name=#{name},"
      + "scheTime=#{scheTime},firstClassPrice=#{firstClassPrice},"
      + "secondClassPrice=#{secondClassPrice} WHERE id=#{id}")
  boolean update(Flight flight);

  @Insert("INSERT INTO Flight VALUES(#{id},#{airlineId},#{airplaneId},#{name},#{scheTime},"
      + "#{firstClassPrice},#{secondClassPrice)")
  boolean insert(Flight flight);
}
