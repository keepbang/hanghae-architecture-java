package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.ApplyCounter;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 * create on 3/28/24. create by IntelliJ IDEA.
 *
 * <p> 특강 신청 수강생수를 저장하는 repository </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface ApplyCounterJpaRepository extends JpaRepository<ApplyCounter, Long> {

  // 비관적 락 적용
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<ApplyCounter> findAndLockByLectureId(Long id);

}
