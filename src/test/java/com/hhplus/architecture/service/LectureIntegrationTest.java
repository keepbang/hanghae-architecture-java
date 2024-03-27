package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.architecture.dto.CreateLectureRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 특강 신청 동시성 테스트 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LectureIntegrationTest {

  @Autowired
  private LectureService lectureService;


  @Test
  @DisplayName("30명 정원인 특강 3개에 120이 신청할려고할 경우")
  void applyLecture_Concurrent_특강여러개() throws InterruptedException {
    // given
    int numberOfThreads = 120;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);

    // 특강 등록
    saveLecture("목요일 특강");
    saveLecture("토요일 특강");
    saveLecture("금요일 특강");

    // 실패한 사용자를 담기위한 큐
    ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

    // when - 특강 신청
    for (int i = 0; i < numberOfThreads; i++) {
      int finalI = i;
      executorService.execute(() -> {
        try {
          long lectureId = (finalI % 3) + 1L; // 특강 아이디.
          lectureService.userApply(finalI + 1L, lectureId); // 특강 신청
        } catch (Exception e) {
          queue.add(finalI + 1L);
        }
        latch.countDown();
      });
    }
    latch.await();
    // then
    Long count1 = lectureService.countApplyUserByLectureId(1L);
    Long count2 = lectureService.countApplyUserByLectureId(2L);
    Long count3 = lectureService.countApplyUserByLectureId(3L);

    // 3특강에 신청 수강생 수는 30이여야 한다.
    assertThat(count1).isEqualTo(30L);
    assertThat(count2).isEqualTo(30L);
    assertThat(count3).isEqualTo(30L);

    // 실패한 사용자 수 검증
    assertThat(queue.size()).isEqualTo(30L);

  }

  private void saveLecture(String name) {
    lectureService.saveLecture(
        new CreateLectureRequest(
            name,
            30L,
            LocalDateTime.now()
                .plusDays(1L)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli(),
            LocalDateTime.now()
                .plusDays(3L)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()
        )
    );
  }

}
