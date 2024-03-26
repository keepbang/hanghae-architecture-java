package com.hhplus.architecture.dto;

import com.hhplus.architecture.common.exception.CreateLectureException;
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

  public void validRequest() {
    if (StringUtils.isEmpty(this.name) || maxUser <= 0) {
      throw new CreateLectureException("잘못된 입력입니다.");
    }

  }

}
