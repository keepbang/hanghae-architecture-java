package com.hhplus.architecture.dto;

import java.time.LocalDateTime;

/**
 * create on 3/23/24. create by IntelliJ IDEA.
 *
 * <p> 특강 등록 response </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record UserSingUpResponse(
    LocalDateTime singUpDateTime // 특강 등록 시간
) {

}
