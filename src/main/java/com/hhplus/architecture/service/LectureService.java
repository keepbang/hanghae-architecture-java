package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.dto.LectureHistoryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 3/23/24. create by IntelliJ IDEA.
 *
 * <p> 강의 관련 서비스 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
public class LectureService {

  private final LectureManager lectureManager;
  private final LectureHistoryManager lectureHistoryManager;
  private final LectureValidator lectorValidator;

  public LectureService(LectureManager lectureManager,
      LectureHistoryManager lectureHistoryManager,
      LectureValidator lectorValidator) {
    this.lectureManager = lectureManager;
    this.lectureHistoryManager = lectureHistoryManager;
    this.lectorValidator = lectorValidator;
  }


  /**
   * 강의 등록
   *
   * @param name               강의 이름.
   * @param maxUser            최대 수강생 수.
   * @param startApplyMillis   신청 시작 시간.
   * @param startLectureMillis 신청 종료 시간.
   * @return 저장된 강의.
   */
  public LectureDto saveLecture(String name, long maxUser, long startApplyMillis,
      long startLectureMillis) {
    return lectureManager.save(name, maxUser, startApplyMillis, startLectureMillis);
  }

  /**
   * 사용자 특강 신청.
   *
   * @param userId    사용자 아이디.
   * @param lectureId 강의 아이디.
   */
  @Transactional
  public LectureHistoryDto userApply(Long userId, Long lectureId) {
    LectureDto lectureDto = lectureManager.findById(lectureId);

    lectorValidator.validUserCount(
        lectureHistoryManager.countApplyByLectureId(lectureId),
        lectureDto.maxUser()
    );

    lectorValidator.validAlreadyApply(
        lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(userId, lectureId)
    );

    return lectureHistoryManager.save(userId, lectureId);
  }

  /**
   * 강의 조회.
   *
   * @param lectureId 강의 아이디.
   * @return 강의 정보.
   */
  public Long countApplyUserByLectureId(Long lectureId) {
    return lectureHistoryManager.countApplyByLectureId(lectureId);
  }

  public Boolean isApplyByLectureIdAndUserId(Long lectureId, Long userId) {
    return lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(userId, lectureId);
  }
}
