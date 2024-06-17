package com.teamsparta.courseregistration.domain.course.dto

/**
 * 과목 응답 DTO
 */
data class CourseResponse(
    val id: Long?,
    val title: String,
    val description: String?,
    val status: String,
    val maxApplicants: Int,
    val numApplicants: Int,
)