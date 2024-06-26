package com.teamsparta.courseregistration.domain.course.controller

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseregistration.domain.course.service.CourseService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/courses")
@RestController
class CourseController(
    private val courseService: CourseService
) {

    // 검색 기능
    @GetMapping("/search")
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    fun searchCourseList(@RequestParam(name = "title") title: String): ResponseEntity<List<CourseResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.searchCourseList(title))
    }

    // 과목 목록 조회
    @GetMapping
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    fun getCourseList(
        @PageableDefault(size = 15, sort = ["id"]) pageable: Pageable,
        @RequestParam(value = "status", required = false) status: String?
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getPaginatedCourseList(pageable, status))
    }

    // 특정 과목 조회
    @GetMapping("/{courseId}")
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    fun getCourse(@PathVariable courseId: Long): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseById(courseId))
    }

    // 과목 생성
    @PostMapping
    @PreAuthorize("hasRole('TUTOR')")
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.createCourse(createCourseRequest))
    }

    // 과목 업데이트
    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('TUTOR')")
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    // 과목 삭제
    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasRole('TUTOR')")
    fun deleteCourse(@PathVariable courseId: Long): ResponseEntity<Unit> {
        courseService.deleteCourse(courseId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}
