package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.FeedbackMapper;
import com.hepangda.keshe.model.Feedback;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackMapper mapper;

  public boolean add(Feedback feedback) {
    return mapper.insert(feedback);
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(Feedback feedback) {
    return mapper.update(feedback);
  }

  public Feedback getById(long id) {
    return mapper.selectById(id);
  }

  public List<Feedback> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }
}
