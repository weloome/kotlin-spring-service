package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.entity.Course
import com.kotlinspring.repository.CourseRepository
import com.kotlinspring.util.courseEntityList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        val courses = courseEntityList()
        courseRepository.saveAll(courses)
    }

    @Test
    fun addCourse() {
        val courseDTO = CourseDTO(null, "Build Restful APIs using SpringBoot and Kotlin", "Danbi")

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

    @Test
    fun retrieveAllCourses() {
        val courseDTO = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        println("courseDTOs : $courseDTO")
        assertEquals(3, courseDTO!!.size)
    }

    @Test
    fun updateCourse() {
        //existing course
        val course = Course(null,
            "Build RestFul APIs using SpringBoot and Kotlin",
                "Development")
        courseRepository.save(course)

        //courseId
        //updated courseId
        val updatedCourseDTO = CourseDTO(null,
            "Build Restful APIs using SpringBoot and Kotlin1",
            "Danbi")

        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", course.id)
            .bodyValue(updatedCourseDTO)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals("Build Restful APIs using SpringBoot and Kotlin1", updatedCourse!!.name)
    }
}