package com.teamsparta.courseregistration.domain.courseapplication.controller

import com.teamsparta.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/courses/{courseId}/applications")
@RestController
class CourseApplicationController {

    @GetMapping()
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>> {
        // TODO: 구현 필요
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long
    ): ResponseEntity<CourseApplicationResponse> {
        // TODO: 구현 필요
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun applyCourse(
        @PathVariable courseId: Long,
        @RequestBody applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse> {
        // TODO: 구현 필요
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): ResponseEntity<CourseApplicationResponse> {
        // TODO: 구현 필요
        return ResponseEntity.ok().build()
    }
}
