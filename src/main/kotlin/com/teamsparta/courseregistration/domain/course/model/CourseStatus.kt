package com.teamsparta.courseregistration.domain.course.model

import com.fasterxml.jackson.annotation.JsonCreator
import org.apache.commons.lang3.EnumUtils

/**
 * 과목 상태를 나타내는 Enum 클래스
 */
enum class CourseStatus {
    OPEN,
    CLOSED;

    companion object {

        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(name: String): CourseStatus? =
            name.let { EnumUtils.getEnumIgnoreCase(CourseStatus::class.java, it.trim()) }
    }
}
