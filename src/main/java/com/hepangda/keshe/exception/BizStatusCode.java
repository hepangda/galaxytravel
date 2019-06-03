package com.hepangda.keshe.exception;

import com.hepangda.keshe.annotation.StatusMessage;

public enum BizStatusCode {
  @StatusMessage("ok")
  OK,

  @StatusMessage("Not found")
  NotFound,

  @StatusMessage("Unexcepted error")
  Unexcepted,

  @StatusMessage("SQL error in Users")
  SqlErrorUser,
}
