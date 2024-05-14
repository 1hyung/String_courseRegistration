package com.teamsparta.courseregistration.domain.course.controller

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses") //courses 관련된거 CourseController가 담당하게 된다.
//애를 빈으로 등록해줘!, 데이터만 리턴하기 때문에 Controller 어노테이션도 쓸 수 있지만
@RestController// 이러면 controller 자체가 빈으로 등록된다. 이 컨트롤러가 어떤 URL을 서빙을 하느냐를 알려줘야함
class CourseController {
    // 이것을 알랴주는 것이 Handler Mapping 알려주는 것이다.
//controller 상위에 RequestMapping이 붙어있으면 하위에 있는 메소드 같은 경우에는 @RequestMapping("/courses 뒤로 온다
    @GetMapping()
    fun getCourseList(): ResponseEntity<List<CourseResponse>> {
        TODO()
    }

    @GetMapping("/{courseId}")
    fun getCourse(@PathVariable courseId: Long): ResponseEntity<CourseResponse> {
        TODO()
    }

    @PostMapping
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity<CourseResponse> {
        TODO()
    }

    //생성을 하면 수정을 할 수 있어야 함
    @PutMapping("/{courseId}")
    fun updateCourse(@PathVariable courseId: Long, @RequestBody updateCourseRequest: UpdateCourseRequest): ResponseEntity<CourseResponse> {
        TODO()
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId: Long) {
        TODO()
    }
}