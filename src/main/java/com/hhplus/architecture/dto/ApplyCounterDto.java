package com.hhplus.architecture.dto;

import com.hhplus.architecture.common.exception.LectureApplyException;

/**
 * create on 3/28/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record ApplyCounterDto(
    Long lectureId,
    Long applyCount,
    Long maxUser
) {

  public void validUserCount() {
    if (this.applyCount >= this.maxUser) {
      throw new LectureApplyException("최대 신청수를 초과했습니다.");
    }
  }

  // applyCount 증가
  public ApplyCounterDto increase() {
    validUserCount();

    return new ApplyCounterDto(this.lectureId, this.applyCount + 1, this.maxUser);
  }
}
