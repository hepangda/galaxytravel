package com.hepangda.keshe.exception;

import com.hepangda.keshe.util.StatusCodeConverter;

public class BizException extends RuntimeException {

  public BizException(BizStatusCode code, String bizMessage) {
    super(StatusCodeConverter.getMessage(code));
    this.bizMessage = bizMessage;
  }

  public String getBizMessage() {
    return bizMessage;
  }

  private String bizMessage;
}
