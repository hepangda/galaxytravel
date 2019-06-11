package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.FlightMapper;
import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

  @Autowired
  private FlightMapper mapper;

  public boolean add(Flight flight) {
    return mapper.insert(flight);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Flight flight) {
    return mapper.update(flight);
  }

  public Flight getById(long id) {
    return mapper.selectById(id);
  }

  public List<Flight> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }
}
