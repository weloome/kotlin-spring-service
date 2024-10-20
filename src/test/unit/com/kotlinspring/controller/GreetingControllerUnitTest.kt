package com.kotlinspring.controller

import com.kotlinspring.service.GreetingsService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingController::class])
// Spring MVC 컴포넌트만을 대상으로 하는 테스트를 구성합니다.
// 전체 애플리케이션 컨텍스트를 로드하지 않고, 지정된 컨트롤러(여기서는 GreetingController)와
// 관련된 Spring MVC 인프라만을 로드합니다.
// 이는 웹 계층의 단위 테스트에 적합하며, 전체 애플리케이션 컨텍스트를 로드하는 것보다 가볍고 빠릅니다.
@AutoConfigureWebTestClient
// WebTestClient를 자동으로 구성합니다.
// @WebMvcTest와 함께 사용될 때, MockMvc 기반의 WebTestClient를 제공합니다.
// 이를 통해 HTTP 요청을 시뮬레이션하고 응답을 검증할 수 있는 편리한 API를 사용할 수 있습니다.
// Spring WebFlux가 아닌 전통적인 Spring MVC 애플리케이션에서도 사용 가능합니다.
class GreetingControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingsServiceMock: GreetingsService

    @Test
    fun retrieveGreeting() {

        val name = "Danbi"
        val expectedResponse = "$name, Hello from default profile"
        every { greetingsServiceMock.retrieveGreeting(any()) } returns expectedResponse

        webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java)
            .isEqualTo(expectedResponse)

        verify(exactly=1) { greetingsServiceMock.retrieveGreeting(name) }
    }
}