package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.architecture.dto.LectureDto;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 강의 신청 동시성 테스트 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
public class LectureIntegrationTest {

  @Autowired
  private LectureService lectureService;

  @Test
  void 동시성_실패_테스트() throws InterruptedException {
    // given
    int numberOfThreads = 101;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);
    LectureDto dto = lectureService.saveLecture(
        "토요 특강",
        100L,
        System.currentTimeMillis(),
        LocalDateTime.now()
            .plusDays(3L)
            .getNano()
    );

    // when
      for (int i = 0; i < numberOfThreads; i++) {
        int finalI = i;
        executorService.execute(() -> {
          try {
            lectureService.userApply(finalI + 1L, 1L);
          } catch(Exception e) {
            e.printStackTrace();
          }
          latch.countDown();
        });
      }
    latch.await();
    // then
    Long count = lectureService.countApplyUserByLectureId(1L);

    assertThat(count).isEqualTo(100L);

  }

}
