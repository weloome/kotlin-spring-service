package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.service.CourseService
import com.kotlinspring.util.courseDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertTrue

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient : WebTestClient

    @MockkBean
    lateinit var courseServiceMockk : CourseService

    @Test
    fun addCourse() {
        val courseDTO = CourseDTO(null, "Build Restful APIs using SpringBoot and Kotlin", "Danbi")

        every { courseServiceMockk.addCourse(any()) } returns courseDTO(id=1)

        val savedCourseDTO = webTestClient
            .post() // post 요청
            .uri("/v1/courses") // 요청 보낼 uri
            .bodyValue(courseDTO) // 요청 본문에 courseDTO 객체 설정
            .exchange() // 요청을 서버로 전송하고 응답 받음
            .expectStatus().isCreated // 응답 상태 201 created 확인
            .expectBody(CourseDTO::class.java) // 응답 본문 CourseDTO 타입 역직렬화
            .returnResult() // 테스트 결과 반환
            .responseBody // 응답 본문 추출

        assertTrue { savedCourseDTO!!.id != null }
    }
}