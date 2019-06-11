package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirportMapper;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.model.Airport;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

  @Autowired
  private AirportMapper mapper;

  public boolean add(Airport airport) {
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
}
