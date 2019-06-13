package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Order;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {

  @Select("SELECT * FROM Orders WHERE id=#{id}")
  Order selectById(long id);

  @Select("SELECT COUNT(*) FROM Orders")
  long count();

  @Select("SELECT COUNT(*) FROM Orders WHERE userId=#{id}")
  long countUser(long id);

  @Select("SELECT * FROM Orders WHERE userId=#{userId} LIMIT #{offset},#{count}")
  List<Order> selectByUserId(@Param("userId") long userId, @Param("offset") int offset,
      @Param("count") int count);

  @Select("SELECT * FROM Orders LIMIT #{offset},#{count}")
  List<Order> selectLimit(@Param("offset") int offset, @Param("count") int count);

  @Delete("DELETE FROM Orders WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Orders SET userId=#{userId},flightId=#{flightId},clazz=#{clazz},cost=#{cost},"
      + "type=#{type},row=#{row},col=#{col} WHERE id=#{id}")
  boolean update(Order order);

  @Insert("INSERT INTO Orders VALUES(#{id},#{userId},#{flightId},#{clazz},#{cost},#{type},#{row},#{col})")
  boolean insert(Order order);
}
