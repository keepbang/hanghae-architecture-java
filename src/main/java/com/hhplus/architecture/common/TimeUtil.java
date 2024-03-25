package com.hhplus.architecture.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 시간관련 유틸 클래스 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public final class TimeUtil {

  public static LocalDateTime millisToLocalDateTime(long millis) {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(
            millis
        ), ZoneId.systemDefault()
    );
  }

}
