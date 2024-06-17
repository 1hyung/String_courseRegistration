package com.teamsparta.courseregistration.domain.course.dto

/**
 * 과목 업데이트 요청 DTO
 */
data class UpdateCourseRequest(
    val title: String,
    val description: String?
)
