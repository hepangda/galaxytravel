package com.hepangda.keshe.util;

import com.hepangda.keshe.annotation.StatusMessage;
import com.hepangda.keshe.exception.BizStatusCode;
import com.hepangda.keshe.exception.FrameworkException;
import com.hepangda.keshe.exception.FrameworkStatusCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StatusCodeConverter {

  private static Map<BizStatusCode, String> converter = new HashMap<>();

  static {
    try {
      var statusClass = Class.forName("com.hepangda.keshe.exception.BizStatusCode");

      var fields = statusClass.getDeclaredFields();
      for (var field : fields) {
        if (field.getName().startsWith("$")) {
          continue;
        }

        var message = Optional.ofNullable(field.getAnnotation(StatusMessage.class))
            .orElseThrow(() -> new FrameworkException(
                "Field `" + field.getName() + "` don't have StatusMessage annotation"
            ));
        field.setAccessible(true);
        converter.put(((BizStatusCode) field.get(BizStatusCode.class)), message.value());
      }
    } catch (Exception ex) {
      System.err.println("Panic: " + ex.getMessage());
      System.exit(FrameworkStatusCode.CODE_FAULT);
    }
  }

  public static String getMessage(BizStatusCode statusCode) {
    return converter.get(statusCode);
  }
}
