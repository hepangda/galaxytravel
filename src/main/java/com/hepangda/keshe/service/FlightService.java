package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.FlightMapper;
import com.hepangda.keshe.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

  @Autowired
  private FlightMapper mapper;

  public Flight getById(long id) {
    return mapper.selectById(id);
  }

  public boolean create(Flight flight) {
    return mapper.insert(flight);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Flight flight) {
    return mapper.update(flight);
  }
}
