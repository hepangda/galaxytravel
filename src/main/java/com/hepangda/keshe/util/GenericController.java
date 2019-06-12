package com.hepangda.keshe.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hepangda.keshe.exception.BizException;
import com.hepangda.keshe.exception.FrameworkException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.Model;

public class GenericController {

  protected <T> String resp(Model model, Supplier<T> supplier, Supplier<String> notNull,
      Supplier<String> ifNull) {
    try {
      if (supplier.get() != null) {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_YES);
        return notNull.get();
      } else {
        model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
        return ifNull.get();
      }
    } catch (BizException ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_BIZMSG, ex.getBizMessage());
    } catch (Exception ex) {
      model.addAttribute(Constants.BIZF_ISOK, Constants.BIZF_ISOK_NO);
      model.addAttribute(Constants.GBF_MESSAGE, ex.getMessage());
    }

    return ifNull.get();
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
        System.out.println("Copy: [" + i.getKey() + "] = " + i.getValue());
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
}
