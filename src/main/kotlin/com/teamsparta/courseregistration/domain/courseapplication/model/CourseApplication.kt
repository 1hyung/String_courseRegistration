package com.teamsparta.courseregistration.domain.courseapplication.model

import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.course.model.toResponse
import com.teamsparta.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.user.model.User
import com.teamsparta.courseregistration.domain.user.model.toResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "course_application")
class CourseApplication(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    val course: Course,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: CourseApplicationStatus = CourseApplicationStatus.PENDING
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isProceeded(): Boolean {
        return status != CourseApplicationStatus.PENDING
    }

    fun accept() {
        status = CourseApplicationStatus.ACCEPTED
    }

    fun reject() {
        status = CourseApplicationStatus.REJECTED
    }
}

/**
 * CourseApplication 엔티티를 CourseApplicationResponse DTO로 변환하는 확장 함수
 */
fun CourseApplication.toResponse(): CourseApplicationResponse {
    return CourseApplicationResponse(
        id = id!!,
        course = course.toResponse(),
        user = user.toResponse(),
        status = status.name
    )
}
