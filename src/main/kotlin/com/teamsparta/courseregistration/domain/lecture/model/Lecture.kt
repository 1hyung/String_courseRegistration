package com.teamsparta.courseregistration.domain.lecture.model

import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.lecture.dto.LectureResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "lecture")
class Lecture(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "video_url", nullable = false)
    var videoUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    var course: Course
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

/**
 * Lecture 엔티티를 LectureResponse DTO로 변환하는 확장 함수
 */
fun Lecture.toResponse(): LectureResponse {
    return LectureResponse(
        id = id!!,
        title = title,
        videoUrl = videoUrl,
    )
}
