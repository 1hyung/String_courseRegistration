package com.teamsparta.courseregistration.domain.course.model

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.lecture.model.Lecture
import com.teamsparta.courseregistration.domain.lecture.model.toResponse
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "course")
class Course(
    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CourseStatus = CourseStatus.OPEN,

    @Column(name = "max_applicants")
    val maxApplicants: Int = 30,

    @Column(name = "num_applicants")
    var numApplicants: Int = 0,

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var lectures: MutableList<Lecture> = mutableListOf(),

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var courseApplications: MutableList<CourseApplication> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    /**
     * 과목의 최대 지원자 수에 도달했는지 확인
     */
    fun isFull(): Boolean {
        return numApplicants >= maxApplicants
    }

    /**
     * 과목이 마감되었는지 확인
     */
    fun isClosed(): Boolean {
        return status == CourseStatus.CLOSED
    }

    /**
     * 과목 상태를 마감으로 변경
     */
    fun close() {
        status = CourseStatus.CLOSED
    }

    /**
     * 지원자 추가
     */
    fun addApplicant() {
        numApplicants += 1
    }

    /**
     * 강의 추가
     */
    fun addLecture(lecture: Lecture) {
        lectures.add(lecture)
    }

    /**
     * 강의 삭제
     */
    fun removeLecture(lecture: Lecture) {
        lectures.remove(lecture)
    }

    /**
     * 과목 신청 추가
     */
    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }
}

/**
 * Course 엔티티를 CourseResponse DTO로 변환하는 확장 함수
 */
fun Course.toResponse(): CourseResponse {
    return CourseResponse(
        id = id!!,
        title = title,
        description = description,
        status = status.toString(),
        maxApplicants = maxApplicants,
        numApplicants = numApplicants,
        lectures = lectures.map { it.toResponse() } // Lecture 엔티티를 LectureResponse DTO로 변환
    )
}
