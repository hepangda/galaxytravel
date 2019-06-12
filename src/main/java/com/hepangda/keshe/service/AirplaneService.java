package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirplaneMapper;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService {

  @Autowired
  private AirplaneMapper mapper;

  public boolean add(Airplane airplane) {
    airplane.setId(IdUtils.nextId());
    return mapper.insert(airplane);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Airplane airplane) {
    return mapper.update(airplane);
  }

  public Airplane getById(long id) {
    return mapper.selectById(id);
  }

  public List<Airplane> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

  public Map<String, String> validate(Airplane airplane) {
    Map<String, String> result = new HashMap<>();

    if (airplane == null) {
      result.put("_airplane", "飞机不能为null");
      return result;
    }

    if (airplane.getName() == null || airplane.getName().isEmpty()) {
      result.put("name", "飞机名不能为空白");
    } else if (airplane.getName().length() >= 30) {
      result.put("name", "飞机名应当小于30个字符");
    }

    if (airplane.getType() == null) {
      result.put("type", "必须指定飞机类型");
    } else if (airplane.getType() > 1 || airplane.getType() < 0) {
      result.put("type", "非法飞机类型值");
    }

    if (airplane.getRows() == null) {
      result.put("rows", "必须指定行数");
    } else if (airplane.getRows() > 100 || airplane.getRows() <= 0) {
      result.put("rows", "座位行数应当在1~100之间");
    }

    if (airplane.getCols() == null) {
      result.put("cols", "必须指定列数");
    } else if (airplane.getCols() > 10 || airplane.getCols() <= 0) {
      result.put("cols", "座位列数应当在1~10之间");
    }

    if (airplane.getFirstClassRows() == null) {
      result.put("cols", "必须指定头等舱行数");
    } else if (airplane.getCols() <= 0 || (
        airplane.getRows() != null && airplane.getFirstClassRows() > airplane.getRows())) {
      result.put("cols", "头等舱行数应当大于0，并小于总行数");
    }

    return result;
  }
}
