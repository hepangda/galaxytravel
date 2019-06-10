package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.FeedbackMapper;
import com.hepangda.keshe.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackMapper mapper;

  public Feedback getById(long id) {
    return mapper.selectById(id);
  }

  public boolean create(Feedback feedback) {
    return mapper.insert(feedback);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Feedback feedback) {
    return mapper.update(feedback);
  }
}
