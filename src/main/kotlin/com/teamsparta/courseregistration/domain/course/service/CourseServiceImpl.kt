package com.teamsparta.courseregistration.domain.course.service

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.course.model.CourseStatus
import com.teamsparta.courseregistration.domain.course.model.toResponse
import com.teamsparta.courseregistration.domain.course.repository.CourseRepository
import com.teamsparta.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplicationStatus
import com.teamsparta.courseregistration.domain.courseapplication.model.toResponse
import com.teamsparta.courseregistration.domain.courseapplication.repository.CourseApplicationRepository
import com.teamsparta.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.courseregistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseregistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseregistration.domain.lecture.dto.UpdateLectureRequest
import com.teamsparta.courseregistration.domain.lecture.model.Lecture
import com.teamsparta.courseregistration.domain.lecture.model.toResponse
import com.teamsparta.courseregistration.domain.lecture.repository.LectureRepository
import com.teamsparta.courseregistration.domain.user.repository.UserRepository
import com.teamsparta.courseregistration.infra.aop.StopWatch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val lectureRepository: LectureRepository,
    private val courseApplicationRepository: CourseApplicationRepository,
    private val userRepository: UserRepository,
) : CourseService {

    override fun getPaginatedCourseList(pageable: Pageable, status: String?): Page<CourseResponse> {
        val courseStatus = when (status) {
            "OPEN" -> CourseStatus.OPEN
            "CLOSED" -> CourseStatus.CLOSED
            null -> null
            else -> throw IllegalArgumentException("The status is invalid")
        }

        return courseRepository.findByPageableAndStatus(pageable, courseStatus).map { it.toResponse() }
    }

    override fun getAllCourseList(): List<CourseResponse> {
        return courseRepository.findAll().map { it.toResponse() }
    }

    override fun searchCourseList(title: String): List<CourseResponse> {
        return courseRepository.searchCourseListByTitle(title).map { it.toResponse() }
    }

    private fun findCourseByIdOrThrow(courseId: Long): Course {
        return courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
    }

    private fun findLectureByIdOrThrow(courseId: Long, lectureId: Long): Lecture {
        return lectureRepository.findByCourseIdAndId(courseId, lectureId) ?: throw ModelNotFoundException(
            "Lecture",
            lectureId
        )
    }

    @StopWatch
    override fun getCourseById(courseId: Long): CourseResponse {
        return findCourseByIdOrThrow(courseId).toResponse()
    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        val course = Course(
            title = request.title,
            description = request.description,
            status = CourseStatus.OPEN,
        )
        return courseRepository.save(course).toResponse()
    }

    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        val course = findCourseByIdOrThrow(courseId)
        course.title = request.title
        course.description = request.description
        return courseRepository.save(course).toResponse()
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
        val course = findCourseByIdOrThrow(courseId)
        courseRepository.delete(course)
    }

    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        val course = findCourseByIdOrThrow(courseId)
        val lecture = Lecture(
            title = request.title,
            videoUrl = request.videoUrl,
            course = course
        )
        course.addLecture(lecture)
        courseRepository.save(course)
        return lecture.toResponse()
    }

    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        return findLectureByIdOrThrow(courseId, lectureId).toResponse()
    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        val course = findCourseByIdOrThrow(courseId)
        return course.lectures.map { it.toResponse() }
    }

    @Transactional
    override fun updateLecture(courseId: Long, lectureId: Long, request: UpdateLectureRequest): LectureResponse {
        val lecture = findLectureByIdOrThrow(courseId, lectureId)
        lecture.title = request.title
        lecture.videoUrl = request.videoUrl
        return lectureRepository.save(lecture).toResponse()
    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        val course = findCourseByIdOrThrow(courseId)
        val lecture = findLectureByIdOrThrow(courseId, lectureId)
        course.removeLecture(lecture)
        courseRepository.save(course)
    }

    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        val course = findCourseByIdOrThrow(courseId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", request.userId)

        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)) {
            throw IllegalStateException("Already applied. courseId: $courseId, userId: ${request.userId}")
        }

        val courseApplication = CourseApplication(course = course, user = user)
        course.addCourseApplication(courseApplication)
        courseRepository.save(course)
        return courseApplication.toResponse()
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)
        return application.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        val course = findCourseByIdOrThrow(courseId)
        return course.courseApplications.map { it.toResponse() }
    }

    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        val course = findCourseByIdOrThrow(courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        if (application.isProceeded()) {
            throw IllegalStateException("Application is already proceeded. applicationId: $applicationId")
        }

        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        when (request.status) {
            CourseApplicationStatus.ACCEPTED.name -> {
                application.accept()
                course.addApplicant()
                if (course.isFull()) {
                    course.close()
                }
                courseRepository.save(course)
            }

            CourseApplicationStatus.REJECTED.name -> {
                application.reject()
            }

            else -> {
                throw IllegalArgumentException("Invalid status: ${request.status}")
            }
        }

        return courseApplicationRepository.save(application).toResponse()
    }
}
