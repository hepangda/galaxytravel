package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirplaneMapper;
import com.hepangda.keshe.model.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService {

  @Autowired
  private AirplaneMapper mapper;

  public Airplane getById(long id) {
    return mapper.selectById(id);
  }

  public boolean create(Airplane airplane) {
    return mapper.insert(airplane);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Airplane airplane) {
    return mapper.update(airplane);
  }
}
