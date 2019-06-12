package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirlineMapper;
import com.hepangda.keshe.mapper.AirportMapper;
import com.hepangda.keshe.model.Airline;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {

  @Autowired
  private AirlineMapper airlineMapper;

  @Autowired
  private AirportMapper airportMapper;

  public boolean add(Airline airline) {
    airline.setId(IdUtils.nextId());
    return airlineMapper.insert(airline);
  }

  public boolean deleteById(long id) {
    return airlineMapper.deleteById(id);
  }

  public boolean update(Airline airline) {
    return airlineMapper.update(airline);
  }

  public Airline getById(long id) {
    return airlineMapper.selectById(id);
  }

  public List<Airline> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return airlineMapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

  public Map<String, String> validate(Airline airline) {
    Map<String, String> result = new HashMap<>();

    if (airline == null) {
      result.put("_airline", "航线不能为null");
      return result;
    }

    if (airline.getName() == null || airline.getName().isEmpty()) {
      result.put("name", "航线名不能为空白");
    } else if (airline.getName().length() >= 20) {
      result.put("name", "航线名应当小于20个字符");
    }

    if (airline.getSourcePortId() == null) {
      result.put("sourcePortId", "必须指定航线起飞机场");
    } else if (airportMapper.selectById(airline.getSourcePortId()) == null) {
      result.put("sourcePortId", "航线起飞机场不存在");
    }

    if (airline.getDestPortId() == null) {
      result.put("sourcePortId", "必须指定航线降落机场");
    } else if (airportMapper.selectById(airline.getDestPortId()) == null) {
      result.put("sourcePortId", "航线降落机场不存在");
    }

    if (airline.getType() == null) {
      result.put("type", "必须指定航线类型");
    } else if (airline.getType() > 2 || airline.getType() < 0) {
      result.put("type", "非法航线类型值");
    }

    return result;
  }

}
