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
public record UserApplyResponse(
    boolean isApply, // 특강 신청 여부
    LocalDateTime applyDateTime // 특강 등록 시간
) {

}
