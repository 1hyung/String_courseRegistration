package com.teamsparta.courseregistration.domain.courseapplication.model

import com.teamsparta.courseregistration.domain.course.model.Course
import jakarta.persistence.*
import org.apache.catalina.User

@Entity
@Table(name = "course_application")
class CourseApplication(

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CourseApplicationStatus

    @ManyToOne
    @JoinColumn(name = "course_id")
    val course: Course

    @ManyToOne
    JoinColumn(name = "user_id")
    val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}