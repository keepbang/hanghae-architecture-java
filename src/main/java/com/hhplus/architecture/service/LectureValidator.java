package com.hhplus.architecture.service;

import com.hhplus.architecture.common.exception.LectureApplyException;
import org.springframework.stereotype.Component;

/**
 * create on 3/26/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Component
public class LectureValidator {

  public void validUserCount(long applyCount, long maxUser) {
    if (applyCount >= maxUser) {
      throw new LectureApplyException("최대 신청수를 초과했습니다.");
    }
  }

  public void validAlreadyApply(boolean isAlready) {
    if (isAlready) {
      throw new LectureApplyException("이미 신청했습니다.");
    }
  }
}
