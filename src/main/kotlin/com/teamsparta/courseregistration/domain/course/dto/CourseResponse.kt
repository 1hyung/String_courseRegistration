package com.teamsparta.courseregistration.domain.course.dto

data class CourseResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val status: String,
    val maxApplicants: Int,
    val numApplicants: Int,
)

fun main(args: Array<String>) {
    val courseResponse = CourseResponse(
        id = 1L,
        title = "abc",
        description = null,
        status = "CLOSED",
        maxApplicants = 30,
        numApplicants = 30,
    )

    val (id, title, description, status, maxApplicants) = courseResponse
    println(id)
    println(title)
    println(courseResponse.toString())
}