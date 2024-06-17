package com.teamsparta.courseregistration.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

/**
 * TestAop 클래스는 특정 메소드 호출 전후로 추가 기능을 실행하기 위해 사용되는 Aspect입니다.
 * 이 클래스는 Aspect로서 Pointcut과 Advice가 포함된 객체로, 부가기능을 모듈화하여 구현합니다.
 */
@Aspect
@Component // 이 클래스를 Spring Bean으로 등록
class TestAop {

    /**
     * @Around 어노테이션은 메소드 실행 전후로 부가기능을 실행할 수 있게 합니다.
     * execution 표현식은 특정 메소드에 적용될 수 있는 JoinPoint를 정의합니다.
     * 여기서는 CourseService의 getCourseById 메소드에 적용됩니다.
     */
    @Around("execution(* com.teamsparta.courseregistration.domain.course.service.CourseService.getCourseById(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint) {
        println("AOP START!!") // AOP가 시작되었음을 알림
        joinPoint.proceed() // 실제 메소드를 실행
        println("AOP END!!") // AOP가 종료되었음을 알림
    }
}
