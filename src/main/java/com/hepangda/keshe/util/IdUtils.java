package com.hepangda.keshe.util;

public class IdUtils {
  private static long now() {
    return System.currentTimeMillis() / 1000 - startTimestamp;
  }

  private static long lastId = -1;

  private static long lastTimestamp = -1;

  private static final long startTimestamp = 1559552768L;

  private static final long maxIdInSeconds = 65535;

  public static long nextId() {
    long thisTimestamp = now();
    if (lastTimestamp == thisTimestamp && lastId >= maxIdInSeconds) {
      while (thisTimestamp == lastTimestamp) {
        thisTimestamp = now();
      }
      lastId = 0;
    }

    lastTimestamp = thisTimestamp;
    return thisTimestamp << 16 + (lastId++);
  }
}