package com.hepangda.keshe.mapper;

import com.hepangda.keshe.model.Airplane;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface AirplaneMapper {

  @Select("SELECT * FROM Airplane WHERE id=#{id}")
  Airplane selectById(long id);

  @Select("SELECT * FROM Airplane LIMIT #{offset},#{count}")
  List<Airplane> selectLimit(int offset, int count);

  @Delete("DELETE FROM Airplane WHERE id=#{id}")
  boolean deleteById(long id);

  @Update("UPDATE Airplane SET name=#{name},type=#{type},rows=#{rows},cols=#{cols},"
      + "firstClassRows=#{firstClassRows} WHERE id=#{id}")
  boolean update(Airplane airplane);

  @Insert("INSERT INTO Airplane VALUES(#{id},#{name},#{type},#{rows},#{cols},#{firstClassRows})")
  boolean insert(Airplane airplane);
}
