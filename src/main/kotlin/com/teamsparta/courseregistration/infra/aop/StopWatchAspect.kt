package com.teamsparta.courseregistration.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

/**
 * StopWatch 어노테이션이 붙은 메소드의 실행 시간을 측정하여 로그로 남기는 AOP Aspect입니다.
 */
@Aspect
@Component
class StopWatchAspect {

    private val logger = LoggerFactory.getLogger("Execution Time Logger")

    /**
     * StopWatch 어노테이션이 붙은 메소드를 실행하여 실행 시간을 측정하고 로그로 출력하는 어드바이스입니다.
     */
    @Around("@annotation(com.teamsparta.courseregistration.infra.aop.StopWatch)")
    fun run(joinPoint: ProceedingJoinPoint): Any {
        val stopWatch = StopWatch()

        stopWatch.start()
        val result = joinPoint.proceed()
        stopWatch.stop()

        val timeElapsedMs = stopWatch.totalTimeMillis

        val methodName = joinPoint.signature.name
        val methodArguments = joinPoint.args

        logger.info("메소드 이름: $methodName | 인자: ${methodArguments.joinToString(", ")} | 실행 시간: ${timeElapsedMs}ms")

        return result
    }
}
