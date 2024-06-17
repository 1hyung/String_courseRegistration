package com.teamsparta.courseregistration.domain.lecture.repository

import com.teamsparta.courseregistration.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Lecture 엔티티에 대한 JPA 리포지토리 인터페이스
 */
/**
 * Lecture 엔티티에 대한 JPA 리포지토리입니다.
 */
/**
 * courseId와 lectureId로 강의를 조회하는 메서드입니다.
 * @param courseId 과목 ID.
 * @param id 강의 ID.
 * @return 찾은 Lecture 엔티티 또는 없으면 null을 반환합니다.
 */
interface LectureRepository : JpaRepository<Lecture, Long> {
    fun findByCourseIdAndId(courseId: Long, id: Long): Lecture?
}
