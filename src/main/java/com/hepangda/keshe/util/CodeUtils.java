package com.hepangda.keshe.util;

import com.hepangda.keshe.dto.ResponseDTO;
import com.hepangda.keshe.exception.BizException;
import com.hepangda.keshe.exception.BizStatusCode;
import java.util.function.Supplier;

public class CodeUtils {

  @SuppressWarnings("unchecked")
  public static <T> ResponseDTO<T> response(BizStatusCode failCode, Supplier<T> supplier) {
    try {
      T obj = supplier.get();
      return (ResponseDTO<T>) ResponseDTO.success(obj);
    } catch (BizException ex) {
      return (ResponseDTO<T>) ResponseDTO.fail(failCode, ex.getBizMessage());
    } catch (Exception ex) {
      ex.printStackTrace();
      return (ResponseDTO<T>) ResponseDTO.fail(BizStatusCode.Unexcepted, "");
    }
  }
}
