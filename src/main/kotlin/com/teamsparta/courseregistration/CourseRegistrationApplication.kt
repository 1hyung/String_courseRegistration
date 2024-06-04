package com.teamsparta.courseregistration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableAsync
// Spring AOP인데 AspectJ라는 이름이 붙은 이유는 Spring AOP가 기본적으로 AspectJ를 사용해서 만들었기 때문!
@EnableAspectJAutoProxy // proxy 기반으로 aop를 쓸 수 있음
@SpringBootApplication
class CourseRegistrationApplication

fun main(args: Array<String>) {
    runApplication<CourseRegistrationApplication>(*args)
}
