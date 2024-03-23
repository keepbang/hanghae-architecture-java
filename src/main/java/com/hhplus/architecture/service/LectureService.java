package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.UserSingUpResponse;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 3/23/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class LectureService {



  public UserSingUpResponse userSingUp(Long id) {



    return new UserSingUpResponse(LocalDateTime.now());
  }

}
