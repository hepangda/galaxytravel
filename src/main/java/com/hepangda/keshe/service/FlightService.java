package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.AirlineMapper;
import com.hepangda.keshe.mapper.AirplaneMapper;
import com.hepangda.keshe.mapper.AirportMapper;
import com.hepangda.keshe.mapper.FlightMapper;
import com.hepangda.keshe.model.Airline;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.model.Airport;
import com.hepangda.keshe.model.Flight;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
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

  public boolean add(Flight flight) {
    flight.setId(IdUtils.nextId());
    return flightMapper.insert(flight);
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

  public List<Flight> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return flightMapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

  public List<Flight> search(String fromPort, String toPort, long lowTime, long highTime) {
    List<Flight> flightList = flightMapper.selectTime(lowTime, highTime);
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

    return flightList.stream()
        .filter(i -> airlineIdList.contains(i.getAirlineId()))
        .collect(Collectors.toList());
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
