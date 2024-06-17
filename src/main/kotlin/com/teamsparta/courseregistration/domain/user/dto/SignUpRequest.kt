package com.teamsparta.courseregistration.domain.user.dto

/**
 * 사용자 회원가입 요청 DTO
 */
data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val role: String
)
