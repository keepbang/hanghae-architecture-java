package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.architecture.common.exception.LectureApplyException;
import com.hhplus.architecture.dto.LectureHistoryDto;
import com.hhplus.architecture.stub.StubLectureHistoryManager;
import com.hhplus.architecture.stub.StubLectureManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * create on 2024/03/25. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
class LectureServiceTest {

  public StubLectureHistoryManager lectureHistoryManager = new StubLectureHistoryManager();

  public StubLectureManager lectureManager = new StubLectureManager();

  public LectureService lectureService;

  @BeforeEach
  public void setUp() {
    this.lectureService = new LectureService(
        lectureManager,
        lectureHistoryManager,
        new LectureValidator());
  }

  // 신청자가 초과되면 exception 발생
  @ParameterizedTest
  @ValueSource(ints = {
      30, 31
  })
  @DisplayName("신청자 수가 초과되면 실패한다.")
  void lectureApply_overMaxApplyCount_fileTest(int userCount) {
    // given
    lectureHistoryManager.initList(userCount); // 사용자 추가
    // when
    // then
    assertThatThrownBy(() -> lectureService.userApply(1L, 1L))
        .isInstanceOf(LectureApplyException.class)
        .hasMessage("최대 신청수를 초과했습니다.");
  }

  // 이미 신청했을 경우 exception 발생
  @Test
  @DisplayName("이미 신청한 내역이 존재하면 실패한다.")
  void lectureApply_alreadyApply_failTest() {
    // given
    lectureHistoryManager.initList(1); // 사용자 추가

    // when
    // then
    assertThatThrownBy(() -> lectureService.userApply(1L, 1L))
        .isInstanceOf(LectureApplyException.class)
        .hasMessage("이미 신청했습니다.");
  }

  // 강의 신청 성공.
  @Test
  @DisplayName("강의 신청 성공")
  void lectureApply_ok() {
    // given
    Long userId = 1L;
    Long lectureId = 1L;

    // when
    LectureHistoryDto lectureHistoryDto = lectureService.userApply(userId, lectureId);

    // then
    assertThat(lectureHistoryDto.userId()).isEqualTo(userId);
    assertThat(lectureHistoryDto.lectureId()).isEqualTo(lectureId);
  }

  // 특강 신청 여부 조회 테스트
  @ParameterizedTest
  @CsvSource(value = {
      "1, true", "0, false"
  })
  @DisplayName("특강 신청 여부 조회.")
  void isApplyByLectureIdAndUserId_ok(int count, boolean result) {
    // given
    lectureHistoryManager.initList(count); // 사용자 추가

    // when
    Boolean isApply = lectureService.isApplyByLectureIdAndUserId(1L, 1L);

    // then
    assertThat(isApply).isEqualTo(result);

  }


}