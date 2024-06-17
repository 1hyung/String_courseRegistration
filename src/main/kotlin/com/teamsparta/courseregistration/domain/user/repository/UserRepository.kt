package com.teamsparta.courseregistration.domain.user.repository

import com.teamsparta.courseregistration.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User 엔티티에 대한 JPA 리포지토리 인터페이스
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}
