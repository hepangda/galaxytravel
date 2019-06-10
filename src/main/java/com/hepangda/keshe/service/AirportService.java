package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirportMapper;
import com.hepangda.keshe.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

  @Autowired
  private AirportMapper mapper;

  public Airport getById(long id) {
    return mapper.selectById(id);
  }

  public boolean create(Airport airport) {
    return mapper.insert(airport);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Airport airport) {
    return mapper.update(airport);
  }
}
