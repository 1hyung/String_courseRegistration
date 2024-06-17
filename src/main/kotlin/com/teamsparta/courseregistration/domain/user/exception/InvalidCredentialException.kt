package com.teamsparta.courseregistration.domain.user.exception

/**
 * 잘못된 자격 증명을 나타내는 예외
 * @param message 예외 메시지
 */
data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
) : RuntimeException(message)
