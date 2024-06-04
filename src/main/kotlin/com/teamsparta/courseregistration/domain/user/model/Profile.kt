package com.teamsparta.courseregistration.domain.user.model

import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import jakarta.persistence.*

@Embeddable
class Profile(
    @Column(name = "nickname", nullable = false)
    var nickname: String,
)