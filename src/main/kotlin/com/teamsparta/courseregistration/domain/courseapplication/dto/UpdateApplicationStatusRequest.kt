package com.teamsparta.courseregistration.domain.courseapplication.dto

/**
 * 과목 신청 상태 업데이트 요청 DTO
 */
data class UpdateApplicationStatusRequest(
    val status: String,
)
