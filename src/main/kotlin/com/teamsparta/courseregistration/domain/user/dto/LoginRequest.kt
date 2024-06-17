package com.teamsparta.courseregistration.domain.user.dto

/**
 * 사용자 로그인 요청 DTO
 */
data class LoginRequest(
    val email: String,
    val password: String,
    val role: String,
)
