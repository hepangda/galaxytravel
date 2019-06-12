package com.hepangda.keshe.service;

import com.hepangda.keshe.mapper.UserMapper;
import com.hepangda.keshe.model.Airplane;
import com.hepangda.keshe.model.User;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.IdUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    user.setId(IdUtils.nextId());
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

  public Map<String, String> validate(User user) {
    Map<String, String> result = new HashMap<>();

    if (user == null) {
      result.put("_user", "用户不能为null");
      return result;
    }

    if (user.getUsername() == null || user.getUsername().isEmpty()) {
      result.put("username", "用户名不能为空白");
    } else if (user.getUsername().length() >= 20) {
      result.put("username", "用户名应当小于30个字符");
    }

    if (user.getPwd() == null || user.getPwd().isEmpty()) {
      result.put("pwd", "密码不能为空白");
    } else if (user.getPwd().length() >= 30) {
      result.put("pwd", "密码应当小于30个字符");
    }

    if (user.getType() == null) {
      result.put("type", "必须指定用户类型");
    } else if (user.getType() > 1 || user.getType() < 0) {
      result.put("type", "非法用户类型值");
    }

    if (user.getRealname() == null || user.getRealname().isEmpty()) {
      result.put("realname", "真实姓名不能为空白");
    } else if (user.getRealname().length() >= 20) {
      result.put("realname", "真实姓名应当小于20个字符");
    }

    if (user.getPhone() == null || user.getPhone().isEmpty()) {
      result.put("name", "手机号码不能为空白");
    } else if (user.getPhone().length() >= 15) {
      result.put("name", "手机号码应当小于15个字符");
    }

    return result;
  }
}
