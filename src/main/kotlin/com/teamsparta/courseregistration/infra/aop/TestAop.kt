package com.teamsparta.courseregistration.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

//자체서 aspect가 됨! point값과 advice가 적혀있는 하나의 객체가 되는 것 부가기능을 여기에서 모듈화 한다고 생각하면 됨!
@Aspect
@Component // Bean으로 등록
class TestAop {
    //#4 @Around(pointcut)을 작성해줌 execution 상세하게 컨트롤 할 때 사용
    @Around("execution(* com.teamsparta.courseregistration.domain.course.service.CourseService.getCourseById(..))")  //#2 어디에 정확히 적용이 되는지 joinpoint를 기준으로 어느 위치에 적용이 되는지 표기해줌!
    // 적용될 수 있는 joinPoint를 적어줌 argument로 항상 joinPoint를 받는다고 생각하면 됨
    // * 다 적용해준다. .. 하나 이상의 뭔가가 있다는 표기
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint) {
        println("AOP START!!") //AOP가 시작됐어
        joinPoint.proceed() // 적용된 부분을 실행해 //#3 joinpoint를 기준으로 앞, 뒤로 실행을 할거기 때문
        println("AOP END!!") //끝나면 AOP가 끝났다는 것을 알려줘 #1
    }
}
