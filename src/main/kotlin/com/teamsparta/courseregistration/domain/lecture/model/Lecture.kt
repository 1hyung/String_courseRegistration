package com.teamsparta.courseregistration.domain.lecture.model

import com.teamsparta.courseregistration.domain.course.model.Course
import jakarta.persistence.*

@Entity
@Table(name = "lecture")
class Lecture(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "video_url", nullable = false)
    var videoUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "course_id")
    val course: Course
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}