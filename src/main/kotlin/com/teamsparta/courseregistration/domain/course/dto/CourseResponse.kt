package com.teamsparta.courseregistration.domain.course.dto

import com.teamsparta.courseregistration.domain.lecture.dto.LectureResponse

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
    val lectures: List<LectureResponse> // 여기에 lectures 필드를 추가합니다.
)