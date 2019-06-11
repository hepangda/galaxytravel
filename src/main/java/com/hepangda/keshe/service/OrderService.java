package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.OrderMapper;
import com.hepangda.keshe.model.Order;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderMapper mapper;

  public boolean add(Order order) {
    return mapper.insert(order);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Order order) {
    return mapper.update(order);
  }

  public Order getById(long id) {
    return mapper.selectById(id);
  }

  public List<Order> showUser(long userId, int page) {
    List<Order> result = mapper.selectByUserId(userId);
    int skipCount = (page - 1) * Constants.BIZ_PAGE_BY;
    return result.subList(skipCount, skipCount + Constants.BIZ_PAGE_BY - 1);
  }

  public List<Order> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }
}
