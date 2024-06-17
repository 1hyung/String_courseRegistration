package com.teamsparta.courseregistration.domain.lecture.dto

/**
 * 새로운 강의 추가 요청 DTO
 */
data class AddLectureRequest(
    val title: String,
    val videoUrl: String,
)
