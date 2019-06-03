package com.hepangda.keshe.dto;

import com.hepangda.keshe.exception.BizStatusCode;
import com.hepangda.keshe.util.StatusCodeConverter;
import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class ResponseDTO<T> {

  public static <T> ResponseDTO success(T result) {
    return ResponseDTO.of()
        .setOk(true)
        .setStatusCode(BizStatusCode.OK)
        .setMessage(StatusCodeConverter.getMessage(BizStatusCode.OK))
        .setBizMessage(null)
        .setData(result);
  }

  public static <T> ResponseDTO fail(BizStatusCode code, String bizMessage) {
    return ResponseDTO.of()
        .setOk(false)
        .setStatusCode(code)
        .setMessage(StatusCodeConverter.getMessage(code))
        .setBizMessage(bizMessage)
        .setData(null);
  }

  private boolean ok;

  private BizStatusCode statusCode;

  private String message;

  private String bizMessage;

  private T data;
}
