# kotlin-spring-service

Spring Boot를 사용하여 Kotlin 기본 문법을 공부합니다.  
Udemy의 Pragmatic Code School - "Kotlin 및 Spring Boot를 사용하여 RESTFUL API 구축하기" 강좌의 섹션 11 ~ 20을 기반으로 학습합니다.

## 목차
- [프로젝트 소개](#프로젝트-소개)
- [기술 스택 및 버전 정보](#기술-스택-및-버전-정보)
- [프로젝트 구조](#프로젝트-구조)
- [설치 방법](#설치-방법)
- [참고 자료](#참고-자료)

## 프로젝트 소개
이 프로젝트는 Kotlin과 Spring Boot를 사용하여 RESTful API를 구축하는 방법을 학습한 기록입니다.  
기본적인 Kotlin 문법과 Spring Boot의 설정, API 구현 방법을 배웠습니다.

## 기술 스택 및 버전 정보
- **Kotlin**: 1.9.25
- **Java**: 17 (JDK 17)
- **Spring Boot**: 3.3.4
- **Gradle**: 7.x
- **Testcontainers**: 1.16.2 (테스트 시 사용할 경우)
- **플러그인 버전**:
   - `io.spring.dependency-management`: 1.1.6
   - `kotlin("plugin.jpa")`: 1.9.25

## 프로젝트 구조
```
kotlin-spring-service/
├── src/
│   ├── main/
│   │   ├── kotlin/             # Kotlin 코드 (주요 비즈니스 로직과 컨트롤러, 서비스 등)
│   │   └── resources/          # 설정 파일 (application.yml)
│   └── test/                   # 테스트 코드
│       ├── intg/               # 통합 테스트 코드
│       └── unit/               # 유닛 테스트 코드
├── build.gradle.kts            # Gradle 빌드 파일
├── docker-compose.yml          # Docker 환경 설정 파일
└── README.md                   # 리드미 파일
```

## 설치 방법
### Prerequisites
- PostgresSQL: [설치 방법](https://velog.io/@danv/Postgres-DB)

### 설치 및 실행
1. **프로젝트 클론**:
    ```bash
    git clone https://github.com/your-username/kotlin-spring-service.git
    cd kotlin-spring-service
    ```

2. **의존성 설치 및 빌드**:
    ```bash
    ./gradlew build
    ```

3. **애플리케이션 실행**:
    - Docker Compose를 사용하여 Postgres와 함께 실행:
    ```bash
    docker-compose up
    ```
    - 혹은 로컬에서 직접 실행:
    ```bash
    ./gradlew bootRun
    ```

4. **API 테스트**:  
   기본적으로 `http://localhost:8080`에서 애플리케이션이 실행됩니다.


## 참고 자료
- [Udemy: Pragmatic Code School - Kotlin 및 Spring Boot 를 사용하여 RESTFUL API 구축하기](https://www.udemy.com/course/build-restful-apis-using-kotlin-and-spring-boot-korean/?couponCode=KEEPLEARNING)
