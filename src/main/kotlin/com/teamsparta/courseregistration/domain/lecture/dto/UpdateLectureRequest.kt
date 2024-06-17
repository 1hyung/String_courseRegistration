package com.teamsparta.courseregistration.domain.lecture.dto

/**
 * 기존 강의 업데이트 요청 DTO
 */
data class UpdateLectureRequest(
    val title: String,
    val videoUrl: String,
)
