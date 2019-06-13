package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirplaneMapper;
import com.hepangda.keshe.mapper.FlightMapper;
import com.hepangda.keshe.mapper.OrderMapper;
import com.hepangda.keshe.mapper.UserMapper;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.model.Order;
import com.hepangda.keshe.model.User;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private FlightMapper flightMapper;

  @Autowired
  private AirplaneMapper airplaneMapper;

  public boolean add(Order order) {
    order.setId(IdUtils.nextId());
    return orderMapper.insert(order);
  }

  public boolean deleteById(long id) {
    Order order = getById(id);
    if (order == null) {
      return false;
    }
    if (order.getType() == 1) {
      order.setType(0);
    } else {
      order.setType(1);
    }
    return orderMapper.update(order);
  }

  public boolean update(Order order) {
    return orderMapper.update(order);
  }

  public Order getById(long id) {
    return orderMapper.selectById(id);
  }

  public List<Order> showUser(long userId, int page) {
    List<Order> result = orderMapper.selectByUserId(userId);
    int skipCount = (page - 1) * Constants.BIZ_PAGE_BY;
    return result.subList(skipCount, skipCount + Constants.BIZ_PAGE_BY - 1);
  }

  public List<Order> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return orderMapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

  public Map<String, String> getUserMap() {
    Map<String, String> result = new HashMap<>();
    List<User> users = userMapper.selectAll();

    for (User i : users) {
      result.put(i.getId().toString(), i.getRealname());
    }

    return result;
  }

  public long getPageMax() {
    long count = orderMapper.count();
    if (count % Constants.BIZ_PAGE_BY == 0) {
      return count / Constants.BIZ_PAGE_BY;
    }
    return (count / Constants.BIZ_PAGE_BY) + 1;
  }

  public Map<String, String> getFlightMap() {
    Map<String, String> result = new HashMap<>();
    List<Flight> flights = flightMapper.selectAll();

    for (Flight i : flights) {
      result.put(i.getId().toString(), i.getName());
    }

    return result;
  }


  public Map<String, String> validate(Order order) {
    Map<String, String> result = new HashMap<>();

    if (order == null) {
      result.put("_order", "订单不能为null");
      return result;
    }

    if (order.getUserId() == null) {
      result.put("userId", "必须指定订单用户");
    } else if (userMapper.selectById(order.getUserId()) == null) {
      result.put("userId", "用户不存在");
    }

    if (order.getFlightId() == null) {
      result.put("flightId", "必须指定订单航班");
    } else if (flightMapper.selectById(order.getFlightId()) == null) {
      result.put("flightId", "航班不存在");
    }

    if (order.getClazz() == null) {
      result.put("clazz", "必须指定舱位");
    } else if (order.getType() > 1 || order.getType() < 0) {
      result.put("clazz", "非法舱位类型值");
    }

    if (order.getCost() == null) {
      result.put("cost", "必须指定订单金钱");
    }

    if (order.getType() == null) {
      result.put("type", "必须指定订单类型");
    } else if (order.getType() > 1 || order.getType() < 0) {
      result.put("type", "非法订单类型值");
    }

    if (order.getRow() == null) {
      result.put("row", "必须指定行");
    }

    if (order.getCol() == null) {
      result.put("col", "必须指定列");
    }

    if (order.getFlightId() != null) {
      Flight flight = flightMapper.selectById(order.getFlightId());
      if (flight != null && order.getRow() != null && order.getCol() != null) {
        Airplane airplane = airplaneMapper.selectById(flight.getAirplaneId());

        if (order.getRow() <= 0 || order.getRow() > airplane.getRows()) {
          result.put("row", "该行在飞机上不存在");
        }

        if (order.getCol() <= 0 || order.getCol() > airplane.getCols()) {
          result.put("col", "该列在飞机上不存在");
        }
      }
    }

    return result;
  }
}
