package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.architecture.common.exception.LectureApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * create on 3/26/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class LectureValidatorTest {

  LectureValidator lectureValidator = new LectureValidator();

  // 이미 신청했을 경우 exception 발생
  @Test
  @DisplayName("이미 신청한 내역이 존재하면 실패한다.")
  void validAlreadyApply_alreadyApply_failTest() {
    // given
    // when
    // then
    assertThatThrownBy(() -> lectureValidator.validAlreadyApply(true))
        .isInstanceOf(LectureApplyException.class)
        .hasMessage("이미 신청했습니다.");
  }

}