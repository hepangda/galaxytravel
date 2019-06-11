package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirplaneMapper;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService {

  @Autowired
  private AirplaneMapper mapper;

  public boolean add(Airplane airplane) {
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
}
