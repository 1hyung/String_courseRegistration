package com.teamsparta.courseregistration.domain.user.dto

/**
 * 사용자 응답 DTO
 */
data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
)
