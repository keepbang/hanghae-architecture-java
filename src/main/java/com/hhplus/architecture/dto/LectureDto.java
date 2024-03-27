package com.hhplus.architecture.dto;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 특강 정보. </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public record LectureDto(
    long id, // 특강 번호.
    String name, // 특강 이름.
    long maxUser,  //  최대 수강생 수.
    long startApplyMillis, // 특강 신청 시작 시간.
    long startLectureMillis // 특강 시작 시간.
) {

}
