package com.teamsparta.courseregistration.domain.user.model

import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Embedded
    val profile: Profile,

    //Embeddable(다른 Entity에 종속될 수 있는 애)과 Embedded(종속할 때)는 세트로 볼 수 있음

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: UserRole

    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val couseApplication: MutableList<CourseApplication> = mutableListOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}