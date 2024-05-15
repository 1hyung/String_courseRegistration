package com.teamsparta.courseregistration.domain.courseapplication.dto

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.user.dto.UserResponse

//lecture정보, course정보를 같이 주고싶다 상태만 보고도 하위 url을 조회하지 않고도 같이 주고싶으면
data class CourseApplicationResponse (
    val id: Long,
    val course: CourseResponse,
    val user: UserResponse,
    val status: String
)
