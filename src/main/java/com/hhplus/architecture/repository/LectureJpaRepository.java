package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.Lecture;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 강의 관련 DAO </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

  // 비관적 락 적용
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Lecture> findById(Long id);

}
