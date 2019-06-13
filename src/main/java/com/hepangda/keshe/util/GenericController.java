package com.hepangda.keshe.util;

import com.hepangda.keshe.exception.BizException;
import com.hepangda.keshe.exception.FrameworkException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.Model;

public class GenericController {

  protected <T> String resp(Model model, Supplier<T> supplier, Dealer<T> notNull,
      Dealer<T> ifNull) {
    T obj = supplier.get();
    try {
      if (obj != null) {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_YES);
        return notNull.get(obj);
      } else {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
        return ifNull.get(obj);
      }
    } catch (BizException ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_BIZMSG, ex.getBizMessage());
    } catch (Exception ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_MESSAGE, ex.getMessage());
    }

    return ifNull.get(obj);
  }

  protected <T> String resp(Model model, Supplier<T> supplier, String result) {
    try {
      if (supplier.get() != null) {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_YES);
      } else {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      }
    } catch (BizException ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_BIZMSG, ex.getBizMessage());
    } catch (Exception ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_MESSAGE, ex.getMessage());
    }

    return result;
  }

  protected <T> String resp(Model model, Supplier<T> supplier, Supplier<String> result) {
    try {
      T obj = supplier.get();
      if (obj != null) {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_YES);
      } else {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      }
    } catch (BizException ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_BIZMSG, ex.getBizMessage());
    } catch (Exception ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_MESSAGE, ex.getMessage());
    }

    return result.get();
  }

  protected <T> String resp(Model model, Supplier<T> supplier, String success, String failed) {
    try {
      if (supplier.get() != null) {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_YES);
        return success;
      }
    } catch (BizException ex) {
      model.addAttribute(Constants.GBF_BIZMSG, ex.getBizMessage());
    } catch (Exception ex) {
      model.addAttribute(Constants.GBF_MESSAGE, ex.getMessage());
    }

    model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
    return failed;
  }

  @SuppressWarnings("unchecked")
  protected <T> T getBeanFromBody(Class<T> clazz, Map<String, Object> beanMap) {
    try {
      T result = (T) clazz.getMethod("of").invoke(null);
      for (Entry<String, Object> i : beanMap.entrySet()) {
        BeanUtils.setProperty(result, i.getKey(), i.getValue());
      }
      return result;
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
      throw new FrameworkException("Code fault");
    }
  }

  protected int dealPage(Integer page) {
    return (page == null || page < 1) ? 1 : page;
  }

  public interface Dealer<T> {

    String get(T obj);
  }
}
