package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.architecture.common.exception.MaxApplyException;
import com.hhplus.architecture.repository.StubLectureHistoryRepository;
import com.hhplus.architecture.repository.StubLectureManager;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
class LectureServiceTest {

  public LectureHistoryManager lectureHistoryManager;

  public LectureManager lectureManager;

  public LectureService lectureService;

  @BeforeEach
  public void setUp() {
    this.lectureHistoryManager = new LectureHistoryManager(
        new StubLectureHistoryRepository()
    );

    this.lectureManager = new LectureManager(
        new StubLectureManager()
    );

    this.lectureService = new LectureService(lectureManager, lectureHistoryManager);
  }

  // 신청자가 초과되면 exception 발생
  @Test
  @DisplayName("신청자 수가 초과되면 MaxApplyException 발생")
  void lectureApply_overMaxApplyCount_exceptionTest() {
    // given
    String lectureName = "토요특강";
    long maxUser = 30L;
    long startApplyMillis = System.currentTimeMillis();
    long startLectureMillis = LocalDateTime.now()
        .plusDays(3L)
        .getNano();

    lectureService.saveLecture(lectureName, maxUser, startApplyMillis, startLectureMillis);


    // when, then
    assertThatThrownBy(() -> lectureService.userApply(1L, 1L))
        .isInstanceOf(MaxApplyException.class);
  }

  // 이미 신청했을 경우 exception 발생

}