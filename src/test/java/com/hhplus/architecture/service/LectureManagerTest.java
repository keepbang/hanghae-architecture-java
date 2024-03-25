package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.hhplus.architecture.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class LectureManagerTest {

  @Mock
  private LectureRepository lectureRepository;

  @InjectMocks
  private LectureManager lectureManager;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.lectureManager = new LectureManagerImpl(lectureRepository);
  }

  /**
   * 강의를 못찾을 경우 exception 발생.
   */
  @Test
  void findById_notFoundException() {
    // given
    // when
    when(lectureRepository.findById(anyLong()))
        .thenReturn(Optional.empty());

    // then
    assertThatThrownBy(() -> lectureManager.findById(1L))
        .isInstanceOf(EntityNotFoundException.class);
  }

}