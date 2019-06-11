package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirlineMapper;
import com.hepangda.keshe.model.Airline;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {

  @Autowired
  private AirlineMapper mapper;

  public boolean add(Airline airline) {
    return mapper.insert(airline);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Airline airline) {
    return mapper.update(airline);
  }

  public Airline getById(long id) {
    return mapper.selectById(id);
  }

  public List<Airline> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

}
