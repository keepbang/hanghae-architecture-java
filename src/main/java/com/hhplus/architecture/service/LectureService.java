package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.CreateLectureRequest;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.dto.LectureHistoryDto;
import org.springframework.lang.NonNull;
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
@Transactional(readOnly = true)
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
   * 강의 등록.
   *
   * @param request 강의 등록 요청 정보.
   * @return 저장된 강의.
   */
  @Transactional
  public LectureDto saveLecture(@NonNull CreateLectureRequest request) {

    request.validationRequest();

    return lectureManager.save(
        request.name(),
        request.maxUser(),
        request.startApplyMillis(),
        request.startLectureMillis()
    );
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
   * 특정 강의를 들은 학생 수 조회.
   *
   * @param lectureId 강의 아이디.
   * @return 강의 정보.
   */
  public Long countApplyUserByLectureId(Long lectureId) {
    return lectureHistoryManager.countApplyByLectureId(lectureId);
  }

  /**
   * 강의신청 성공/실패 조회
   *
   * @param lectureId 강의 아이디.
   * @param userId    사용자 아이디.
   * @return 성공/실패 여부.
   */
  public Boolean isApplyByLectureIdAndUserId(Long lectureId, Long userId) {
    return lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(userId, lectureId);
  }
}
