package com.hhplus.architecture.service;

import com.hhplus.architecture.common.exception.DataNotFoundException;
import com.hhplus.architecture.domain.Lecture;
import com.hhplus.architecture.domain.ApplyCounter;
import com.hhplus.architecture.dto.ApplyCounterDto;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.repository.ApplyCounterJpaRepository;
import com.hhplus.architecture.repository.LectureJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * create on 2024/03/25. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
@Component
public class LectureManagerImpl implements LectureManager {

  private final LectureJpaRepository lectureJpaRepository;
  // lecture 와 연관이 깊은 엔티티라 여기에 만들었습니다.
  private final ApplyCounterJpaRepository applyCounterJpaRepository;

  public LectureManagerImpl(final LectureJpaRepository lectureJpaRepository,
      final ApplyCounterJpaRepository applyCounterJpaRepository) {
    this.lectureJpaRepository = lectureJpaRepository;
    this.applyCounterJpaRepository = applyCounterJpaRepository;
  }

  @Override
  public LectureDto save(String name, long maxUser, long startApplyMillis,
      long startLectureMillis) {
    Lecture lecture = lectureJpaRepository.save(new Lecture(
        name, maxUser, startApplyMillis, startLectureMillis
    ));

    applyCounterJpaRepository.save(
        new ApplyCounter(lecture.getId(), maxUser)
    );

    return toDto(lecture);
  }

  @Override
  public List<LectureDto> findAll() {
    return lectureJpaRepository.findAll()
        .stream()
        .map(this::toDto)
        .toList();
  }

  @Override
  public ApplyCounterDto findCountAndLockByLectureId(long lectureId) {
    Optional<ApplyCounter> oApplyCounter = applyCounterJpaRepository.findAndLockByLectureId(lectureId);
    ApplyCounter applyCounter = oApplyCounter.orElseThrow(
        () -> new DataNotFoundException("강의가 등록되지 않았습니다."));
    return new ApplyCounterDto(
        applyCounter.getLectureId(),
        applyCounter.getApplyCount(),
        applyCounter.getMaxUser()
    );
  }

  @Override
  public void saveApplyCount(Long lectureId, ApplyCounterDto applyCounter) {
    applyCounterJpaRepository.save(
        new ApplyCounter(lectureId,
            applyCounter.applyCount(),
            applyCounter.maxUser()
        )
    );
  }

  private LectureDto toDto(Lecture lecture) {
    return new LectureDto(
        lecture.getId(),
        lecture.getName(),
        lecture.getMaxUser(),
        lecture.getStartApplyMillis(),
        lecture.getStartLectureMillis()
    );
  }


}
