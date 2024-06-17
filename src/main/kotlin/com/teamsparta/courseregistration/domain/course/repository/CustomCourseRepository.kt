package com.teamsparta.courseregistration.domain.course.repository

import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.course.model.CourseStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * CustomCourseRepository는 커스텀 쿼리 메서드를 정의하는 인터페이스입니다.
 */
interface CustomCourseRepository {
    /**
     * 주어진 제목을 포함하는 과목 목록을 검색합니다.
     * @param title 과목 제목
     * @return 과목 목록
     */
    fun searchCourseListByTitle(title: String): List<Course>

    /**
     * 페이지 및 상태에 따라 과목 목록을 검색합니다.
     * @param pageable 페이지 정보
     * @param courseStatus 과목 상태
     * @return 페이지로 감싸진 과목 목록
     */
    fun findByPageableAndStatus(pageable: Pageable, courseStatus: CourseStatus?): Page<Course>
}
