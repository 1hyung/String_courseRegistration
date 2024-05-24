package com.teamsparta.courseregistration.domain.course.model

import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.lecture.model.Lecture
import jakarta.persistence.*

@Entity
@Table(name = "course")
class Course(
    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING) // CourseStatus에 있는 이름으로 DB에 저장됨
    @Column(name = "status")
    var status: CourseStatus,

    @Column(name = "max_applicants")
    val maxApplicants: Int = 30,

    @Column(name = "num_applications")
    var numApplications: Int = 0,

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val lectures: MutableSet<Lecture> = mutableSetOf(),

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val courseApplications: MutableList<CourseApplication> = mutableListOf()
) { //ID 같은 경우 constructor에서  받을게 아니기 때문에 {}에서 지정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}