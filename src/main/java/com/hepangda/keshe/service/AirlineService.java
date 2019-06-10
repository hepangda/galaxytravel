package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirlineMapper;
import com.hepangda.keshe.model.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {

  @Autowired
  private AirlineMapper mapper;

  public Airline getById(long id) {
    return mapper.selectById(id);
  }

  public boolean create(Airline airline) {
    return mapper.insert(airline);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Airline airline) {
    return mapper.update(airline);
  }
}
