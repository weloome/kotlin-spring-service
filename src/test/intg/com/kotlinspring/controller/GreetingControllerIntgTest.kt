package com.kotlinspring.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 전체 애플리케이션 컨텍스트를 로드하여 통합 테스트 환경을 구성합니다.
// RANDOM_PORT 설정으로 테스트마다 사용 가능한 랜덤 포트를 할당하여 포트 충돌을 방지합니다.
@ActiveProfiles("test")
// 테스트 실행 시 "test" 프로파일을 활성화합니다.
// 이를 통해 application-test.properties 또는 application-test.yml의 설정을 로드하여 테스트 환경에 특화된 설정을 적용할 수 있습니다.
@AutoConfigureWebTestClient
// WebTestClient를 자동으로 구성합니다.
// 이는 주로 Spring WebFlux 기반의 리액티브 웹 애플리케이션을 테스트하는 데 사용되는 비동기 클라이언트입니다.
// REST API 호출 및 응답 검증을 위한 편리한 메서드들을 제공합니다.
class GreetingControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun retrieveGreeting() {
        val name = "Danbi"
        val result = webTestClient.get()
            .uri("v1/greetings/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals("$name, Hello from default profile", result.responseBody)
    }
}