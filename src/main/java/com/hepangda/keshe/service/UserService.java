package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.UserMapper;
import com.hepangda.keshe.model.User;
import com.hepangda.keshe.util.Constants;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserMapper mapper;

  public User login(User user) {
    Optional<User> originUser = Optional.ofNullable(mapper.selectByUsername(user.getUsername()));

    if (originUser.map(i -> i.getPwd().equals(user.getPwd())).orElse(false)) {
      return originUser.get();
    } else {
      return null;
    }
  }

  public User register(User user) {
    // TODO: 强制set usertype为顾客
    // user.setType();

    // TODO: 验证user的合法性
    return mapper.insert(user) ? user : null;
  }

  public User add(User user) {
    return mapper.insert(user) ? user : null;
  }

  public boolean deleteById(long id) {
    return mapper.deleteById(id);
  }

  public boolean update(User user) {
    return mapper.update(user);
  }

  public User getById(long id) {
    return mapper.selectById(id);
  }

  public User getByName(String username) {
    return mapper.selectByUsername(username);
  }

  public List<User> show(int page) {
    final int offset = Constants.BIZ_PAGE_BY * (page - 1);
    return mapper.selectLimit(offset, Constants.BIZ_PAGE_BY);
  }
}
