package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.Lecture;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface LectureRepository extends JpaRepository<Lecture, Long> {

  Optional<Lecture> findById(Long id);

}
