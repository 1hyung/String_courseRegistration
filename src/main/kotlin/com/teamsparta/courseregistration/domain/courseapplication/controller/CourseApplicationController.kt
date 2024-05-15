package com.teamsparta.courseregistration.domain.courseapplication.controller


import com.teamsparta.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/courses/{courseId}/applications")
@RestController
class CourseApplicationController {

    @GetMapping()
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>> {
        TODO()
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long
    ): ResponseEntity<CourseApplicationResponse> {
        TODO()
    }

    @PostMapping
    fun applyCourse(
        @PathVariable courseId: Long,
        applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse> {
        TODO()
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): ResponseEntity<CourseApplicationResponse> {
        TODO()
    }

}

// CourseApplicationController.kt