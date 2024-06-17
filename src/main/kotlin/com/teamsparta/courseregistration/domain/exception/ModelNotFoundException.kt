package com.teamsparta.courseregistration.domain.exception

//Exception이면 기본적으로 Exception 객체 상속을 받아야 한다.
// 애플리케이션이 실행될 때 발생하는 예외
/**
 * 모델을 찾지 못했을 때 발생하는 예외 클래스
 */
data class ModelNotFoundException(val modelName: String, val id: Long?) :
    RuntimeException("Model $modelName not found with given id: $id")