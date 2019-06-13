package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirlineMapper;
import com.hepangda.keshe.mapper.AirplaneMapper;
import com.hepangda.keshe.mapper.AirportMapper;
import com.hepangda.keshe.mapper.FlightMapper;
import com.hepangda.keshe.mapper.OrderMapper;
import com.hepangda.keshe.model.Airline;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.model.Airport;
import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.model.Order;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

  @Autowired
  private FlightMapper flightMapper;

  @Autowired
  private AirportMapper airportMapper;

  @Autowired
  private AirlineMapper airlineMapper;

  @Autowired
  private AirplaneMapper airplaneMapper;

  @Autowired
  private OrderMapper orderMapper;

  public boolean add(Flight flight) {
    if (flight.getScheTime() == null) {
      return false;
    }
    flight.setId(IdUtils.nextId());
    return flightMapper.insert(flight);
  }

  public List<List<String[]>> getSeat(long clazz, long flightId) {
    List<List<String[]>> result = new ArrayList<>();

    Flight flight = flightMapper.selectById(flightId);
    Airplane airplane = airplaneMapper.selectById(flight.getAirplaneId());

    List<Order> orders = orderMapper.selectByFlightId(flightId);
    if (clazz == 0) {
      for (int i = 1; i <= airplane.getFirstClassRows(); i++) {
        List<String[]> inner = new ArrayList<>();
        for (int j = 1; j <= airplane.getCols(); j++) {
          final int row = i;
          final int col = j;

          if (orders.stream().anyMatch(x -> x.getRow() == row && x.getCol() == col)) {
            inner.add(new String[]{row + "," + col, "seat unavailable"});
          } else {
            inner.add(new String[]{row + "," + col, "seat"});
          }
        }
        result.add(inner);
      }
    } else {
      for (int i = airplane.getFirstClassRows() + 1; i <= airplane.getRows(); i++) {
        List<String[]> inner = new ArrayList<>();
        for (int j = 1; j <= airplane.getCols(); j++) {
          final int row = i;
          final int col = j;

          if (orders.stream().anyMatch(x -> x.getRow() == row && x.getCol() == col)) {
            inner.add(new String[]{row + "," + col, "seat unavailable"});
          } else {
            inner.add(new String[]{row + "," + col, "seat"});
          }
        }
        result.add(inner);
      }
    }
    return result;
  }

  public boolean deleteById(long id) {
    return flightMapper.deleteById(id);
  }

  public boolean update(Flight flight) {
    return flightMapper.update(flight);
  }

  public Flight getById(long id) {
    return flightMapper.selectById(id);
  }

  public List<Flight> show(int page, String from, String to, String time) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    if (AllNull(from, to, time)) {
      return flightMapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
    } else {
      if (from == null) {
        from = "";
      }
      if (to == null) {
        to = "";
      }

      return search(from, to, time);
    }
  }

  public Map<String, String> getLineMap() {
    Map<String, String> result = new HashMap<>();
    List<Airline> airlines = airlineMapper.selectAll();

    for (Airline i : airlines) {
      result.put(i.getId().toString(), i.getName());
    }

    return result;
  }

  public Map<String, String> getPlaneMap() {
    Map<String, String> result = new HashMap<>();
    List<Airplane> airplanes = airplaneMapper.selectAll();

    for (Airplane i : airplanes) {
      result.put(i.getId().toString(), i.getName());
    }

    return result;
  }

  private List<Flight> search(String fromPort, String toPort, String time) {
    System.err.println(fromPort);
    System.err.println(toPort);
    System.err.println(time);
    List<Airport> airportList = airportMapper.selectAll();
    List<Airline> airlineList = airlineMapper.selectAll();

    List<Long> fromPortIdList = airportList.stream()
        .filter(i -> i.getPortName().contains(fromPort))
        .map(Airport::getId)
        .collect(Collectors.toList());

    List<Long> toPortIdList = airportList.stream()
        .filter(i -> i.getPortName().contains(toPort))
        .map(Airport::getId)
        .collect(Collectors.toList());

    List<Long> airlineIdList = airlineList.stream()
        .filter(i -> fromPortIdList.contains(i.getSourcePortId()))
        .filter(i -> toPortIdList.contains(i.getDestPortId()))
        .map(Airline::getId)
        .collect(Collectors.toList());

    List<Flight> flightList;
    if (time == null || time.isEmpty()) {
      flightList = flightMapper.selectAll();
    } else {
      flightList = flightMapper.selectTime(time + " 00:00:00", time + " 23:59:59");
    }
    return flightList.stream()
        .filter(i -> airlineIdList.contains(i.getAirlineId()))
        .collect(Collectors.toList());
  }

  public long getPageMax() {
    long count = flightMapper.count();
    if (count % Constants.BIZ_PAGE_BY == 0) {
      return count / Constants.BIZ_PAGE_BY;
    }
    return (count / Constants.BIZ_PAGE_BY) + 1;
  }

  public boolean AllNull(String... obj) {
    int cnt = 0;
    for (String i : obj) {
      if (i == null || i.trim().isEmpty()) {
        cnt++;
      }
    }

    return cnt == obj.length;
  }

  public Map<String, String> validate(Flight flight) {
    Map<String, String> result = new HashMap<>();

    if (flight == null) {
      result.put("_flight", "航班不能为null");
      return result;
    }

    if (flight.getAirlineId() == null) {
      result.put("airlineId", "必须指定航线");
    } else if (airlineMapper.selectById(flight.getAirlineId()) == null) {
      result.put("airlineId", "航线不存在");
    }

    if (flight.getAirplaneId() == null) {
      result.put("airplaneId", "必须指定飞机");
    } else if (airplaneMapper.selectById(flight.getAirplaneId()) == null) {
      result.put("airplaneId", "该飞机不存在");
    }

    if (flight.getName() == null || flight.getName().isEmpty()) {
      result.put("name", "航班名不能为空白");
    } else if (flight.getName().length() >= 20) {
      result.put("name", "航班名应当小于20个字符");
    }

    if (flight.getScheTime() == null) {
      result.put("scheTime", "必须指定航班飞行时间");
    }

    if (flight.getFirstClassPrice() == null) {
      result.put("firstClassPrice", "必须指定头等舱价格");
    }

    if (flight.getSecondClassPrice() == null) {
      result.put("secondClassPrice", "必须指定经济舱价格");
    }

    return result;
  }
}
