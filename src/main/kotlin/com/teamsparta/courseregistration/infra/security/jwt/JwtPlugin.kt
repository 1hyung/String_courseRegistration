package com.teamsparta.courseregistration.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.Date

/**
 * JWT 토큰을 생성하고 검증하는 클래스입니다.
 * @param issuer 토큰 발급자
 * @param secret 비밀 키
 * @param accessTokenExpirationHour 액세스 토큰 만료 시간(시간 단위)
 */
@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer: String,
    @Value("\${auth.jwt.secret}") private val secret: String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
) {

    /**
     * 주어진 JWT 토큰을 검증합니다.
     * @param jwt JWT 토큰
     * @return 검증된 JWT 클레임
     */
    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt)
        }
    }

    /**
     * 액세스 토큰을 생성합니다.
     * @param subject 주체 (일반적으로 사용자 ID)
     * @param email 사용자 이메일
     * @param role 사용자 역할
     * @return 생성된 JWT 액세스 토큰
     */
    fun generateAccessToken(subject: String, email: String, role: String): String {
        return generateToken(subject, email, role, Duration.ofHours(accessTokenExpirationHour))
    }

    /**
     * 주어진 정보를 기반으로 JWT 토큰을 생성합니다.
     * @param subject 주체 (일반적으로 사용자 ID)
     * @param email 사용자 이메일
     * @param role 사용자 역할
     * @param expirationPeriod 토큰 만료 기간
     * @return 생성된 JWT 토큰
     */
    private fun generateToken(subject: String, email: String, role: String, expirationPeriod: Duration): String {
        val now = Instant.now()
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .setSubject(subject)
            .claim("email", email)
            .claim("role", role)
            .setIssuer(issuer)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(expirationPeriod)))
            .signWith(key)
            .compact()
    }
}
