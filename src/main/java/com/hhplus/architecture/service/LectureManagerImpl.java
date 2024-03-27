package com.hhplus.architecture.service;

import com.hhplus.architecture.domain.Lecture;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.repository.LectureJpaRepository;
import jakarta.persistence.EntityNotFoundException;
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

  public LectureManagerImpl(final LectureJpaRepository lectureJpaRepository) {
    this.lectureJpaRepository = lectureJpaRepository;
  }

  @Override
  public LectureDto save(String name, long maxUser, long startApplyMillis,
      long startLectureMillis) {
    Lecture lecture = lectureJpaRepository.save(new Lecture(
        name, maxUser, startApplyMillis, startLectureMillis
    ));
    return toDto(lecture);
  }

  @Override
  public LectureDto findAndLockById(long id) {
    Optional<Lecture> oLecture = lectureJpaRepository.findAndLockById(id);
    Lecture lecture = oLecture.orElseThrow(EntityNotFoundException::new);
    return toDto(lecture);
  }

  private LectureDto toDto(Lecture lecture) {
    return new LectureDto(lecture.getId(),
        lecture.getName(),
        lecture.getMaxUser(),
        lecture.getStartApplyMillis(),
        lecture.getStartLectureMillis()
    );
  }


}
