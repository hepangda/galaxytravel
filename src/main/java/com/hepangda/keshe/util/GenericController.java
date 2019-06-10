package com.hepangda.keshe.util;

import com.hepangda.keshe.dto.ResponseDTO;
import com.hepangda.keshe.exception.BizException;
import com.hepangda.keshe.exception.BizStatusCode;
import java.util.function.Supplier;
import org.springframework.ui.Model;

public class GenericController {

  @SuppressWarnings("unchecked")
  public static <T> ResponseDTO<T> resp(BizStatusCode failCode, Supplier<T> supplier) {
    try {
      var obj = supplier.get();
      return (ResponseDTO<T>) ResponseDTO.success(obj);
    } catch (BizException ex) {
      return (ResponseDTO<T>) ResponseDTO.fail(failCode, ex.getBizMessage());
    } catch (Exception ex) {
      return (ResponseDTO<T>) ResponseDTO.fail(BizStatusCode.Unexcepted, ex.getMessage());
    }
  }

  public <T> String resp(Model model, Supplier<T> supplier, Supplier<String> notNull,
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

  public <T> String resp(Model model, Supplier<T> supplier, String result) {
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

  public <T> String resp(Model model, Supplier<T> supplier, String success, String failed) {
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

  public int dealPage(Integer page) {
    return (page == null || page < 1) ? 1 : page;
  }
}
