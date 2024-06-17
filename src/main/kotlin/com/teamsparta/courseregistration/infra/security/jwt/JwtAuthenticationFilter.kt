package com.teamsparta.courseregistration.infra.security.jwt

import com.teamsparta.courseregistration.infra.security.JwtAuthenticationToken
import com.teamsparta.courseregistration.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {

    companion object {
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null) {
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    val claims = it.body
                    val userId = claims.subject.toLong()
                    val role = claims["role"] as String
                    val email = claims["email"] as String

                    val principal = UserPrincipal(
                        id = userId,
                        email = email,
                        roles = setOf(role)
                    )
                    // Authentication 구현체 생성
                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        // request로 부터 요청 상세정보 생성
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )
                    // SecurityContext에 authentication 객체 저장
                    SecurityContextHolder.getContext().authentication = authentication
                }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}
