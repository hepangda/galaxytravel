package com.hepangda.keshe.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.hepangda.keshe.mapper.AirportMapper;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.model.Airport;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

  @Autowired
  private AirportMapper mapper;

  public boolean add(Airport airport) {
    airport.setId(IdUtils.nextId());
    return mapper.insert(airport);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Airport airport) {
    return mapper.update(airport);
  }

  public Airport getById(long id) {
    return mapper.selectById(id);
  }

  public List<Airport> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

  public Map<String, String> validate(Airport airport) {
    Map<String, String> result = new HashMap<>();

    if (airport == null) {
      result.put("_airport", "机场不能为null");
      return result;
    }

    if (airport.getCityName() == null || airport.getCityName().isEmpty()) {
      result.put("cityName", "机场所在城市名不能为空白");
    } else if (airport.getCityName().length() >= 20) {
      result.put("cityName", "机场所在城市名应当小于20个字符");
    }

    if (airport.getPortName() == null || airport.getPortName().isEmpty()) {
      result.put("portName", "机场名不能为空白");
    } else if (airport.getPortName().length() >= 20) {
      result.put("portName", "机场名应当小于20个字符");
    }

    if (airport.getType() == null) {
      result.put("type", "必须指定机场类型");
    } else if (airport.getType() > 1 || airport.getType() < 0) {
      result.put("type", "非法机场类型值");
    }

    return result;
  }
}
