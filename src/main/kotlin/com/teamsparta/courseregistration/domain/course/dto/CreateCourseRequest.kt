package com.teamsparta.courseregistration.domain.course.dto

/**
 * 과목 생성 요청 DTO
 */
data class CreateCourseRequest(
    val title: String,
    val description: String,
)
