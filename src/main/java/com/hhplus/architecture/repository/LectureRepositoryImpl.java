package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.Lecture;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 강의 관련 기능 구현 클래스 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
public class LectureRepositoryImpl implements LectureRepository {

  @Override
  public Optional<Lecture> findById(long lectureId) {
    return null;
  }

  @Override
  public Lecture save(String name, long maxUser, long startApplyMillis, long startLectureMillis) {
    return null;
  }


}
