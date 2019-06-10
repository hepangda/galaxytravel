package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.OrderMapper;
import com.hepangda.keshe.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderMapper mapper;

  public Order getById(long id) {
    return mapper.selectById(id);
  }

  public boolean create(Order order) {
    return mapper.insert(order);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Order order) {
    return mapper.update(order);
  }
}
