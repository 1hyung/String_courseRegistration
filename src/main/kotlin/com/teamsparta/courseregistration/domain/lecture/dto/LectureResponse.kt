package com.teamsparta.courseregistration.domain.lecture.dto

/**
 * 강의 응답 DTO
 */
data class LectureResponse(
    val id: Long,
    val title: String,
    val videoUrl: String,
)
