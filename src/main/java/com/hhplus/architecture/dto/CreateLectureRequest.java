package com.hhplus.architecture.dto;

import com.hhplus.architecture.common.exception.InvalidRequestException;
import io.micrometer.common.util.StringUtils;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record CreateLectureRequest(
    String name,
    long maxUser,
    long startApplyMillis,
    long startLectureMillis
) {

  public void validationRequest() {
    // 현재 시간
    long currentMillis = System.currentTimeMillis();

    if (StringUtils.isEmpty(this.name) || this.maxUser <= 0 ||
        this.startApplyMillis < currentMillis || this.startLectureMillis < currentMillis) {
      throw new InvalidRequestException("잘못된 요청입니다.");
    }

  }

}
