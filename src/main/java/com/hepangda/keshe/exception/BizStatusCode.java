package com.hepangda.keshe.exception;

import com.hepangda.keshe.annotation.StatusMessage;

public enum BizStatusCode {
  @StatusMessage("ok")
  OK,

  @StatusMessage("Not found")
  NotFound,

  @StatusMessage("Unexcepted error")
  Unexcepted,

  @StatusMessage("SQL error in User")
  SqlErrorUser,

  @StatusMessage("SQL error in Airline")
  SqlErrorAirline,

  @StatusMessage("SQL error in Feedback")
  SqlErrorFeedback,

  @StatusMessage("SQL error in Flight")
  SqlErrorFlight,

  @StatusMessage("SQL error in Order")
  SqlErrorOrder,

  @StatusMessage("SQL error in Airport")
  SqlErrorAirport,

  @StatusMessage("SQL error in Airplane")
  SqlErrorAirplane,
}
