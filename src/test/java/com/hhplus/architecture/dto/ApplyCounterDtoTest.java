package com.hhplus.architecture.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.architecture.common.exception.LectureApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * create on 3/28/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class ApplyCounterDtoTest {

  // 신청자가 초과되면 exception 발생
  @ParameterizedTest
  @ValueSource(longs = {
      30, 31
  })
  @DisplayName("신청자 수가 초과되면 실패한다.")
  void validUserCount_overMaxApplyCount_fileTest(long userCount) {
    // given
    ApplyCounterDto dto = new ApplyCounterDto(1L, userCount, 30L);
    // when
    // then
    assertThatThrownBy(dto::validUserCount).isInstanceOf(LectureApplyException.class)
        .hasMessage("최대 신청수를 초과했습니다.");
  }

}