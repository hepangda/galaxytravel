package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Feedback;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface FeedbackMapper {

  @Select("SELECT * FROM Feedback WHERE id=#{id}")
  Feedback selectById(long id);

  @Select("SELECT * FROM Feedback LIMIT #{offset},#{count}")
  List<Feedback> selectLimit(@Param("offset") int offset, @Param("count") int count);

  @Select("SELECT * FROM Airport WHERE cityName LIKE CONCAT('%',#{keyword},'%')")
  List<Feedback> selectKeyword(String keyword);

  @Select("SELECT COUNT(*) FROM Feedback")
  long count();

  @Delete("DELETE FROM Feedback WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Feedback SET orderId=#{orderId},message=#{message} WHERE id=#{id}")
  boolean update(Feedback feedback);

  @Insert("INSERT INTO Feedback VALUES(#{id},#{orderId},#{message}")
  boolean insert(Feedback feedback);
}
