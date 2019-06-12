package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.FeedbackMapper;
import com.hepangda.keshe.mapper.OrderMapper;
import com.hepangda.keshe.model.Feedback;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackMapper feedbackMapper;

  @Autowired
  private OrderMapper orderMapper;

  public boolean add(Feedback feedback) {
    feedback.setId(IdUtils.nextId());
    return feedbackMapper.insert(feedback);
  }

  public boolean deleteById(long id) {
    return feedbackMapper.deleteById(id);
  }

  public boolean update(Feedback feedback) {
    return feedbackMapper.update(feedback);
  }

  public Feedback getById(long id) {
    return feedbackMapper.selectById(id);
  }

  public List<Feedback> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return feedbackMapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }

  public Map<String, String> validate(Feedback feedback) {
    Map<String, String> result = new HashMap<>();

    if (feedback == null) {
      result.put("_feedback", "机场不能为null");
      return result;
    }

    if (feedback.getOrderId() == null) {
      result.put("orderId", "必须指定订单号");
    } else if (orderMapper.selectById(feedback.getOrderId()) == null) {
      result.put("orderId", "订单号不存在");
    }

    if (feedback.getMessage() == null || feedback.getMessage().isEmpty()) {
      result.put("message", "反馈信息不能为空白");
    } else if (feedback.getMessage().length() >= 65535) {
      result.put("name", "反馈信息应当小于65535个字符");
    }

    return result;
  }

}
