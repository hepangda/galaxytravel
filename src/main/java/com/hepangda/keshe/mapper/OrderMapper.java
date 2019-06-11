package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Order;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {

  @Select("SELECT * FROM Order WHERE id=#{id}")
  Order selectById(long id);

  @Select("SELECT * FROM Order WHERE userId=#{userId}")
  List<Order> selectByUserId(long userId);

  @Select("SELECT * FROM Order LIMIT #{offset},#{count}")
  List<Order> selectLimit(int offset, int count);

  @Delete("DELETE FROM Order WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Order SET userId=#{userId},flightId=#{flightId},clazz=#{clazz},cost=#{cost},"
      + "type=#{type},row=#{row},col=#{col} WHERE id=#{id}")
  boolean update(Order order);

  @Insert("INSERT INTO Order VALUES(#{id},#{userId},#{flightId},#{clazz},#{cost},#{type},#{row},#{col})")
  boolean insert(Order order);
}
