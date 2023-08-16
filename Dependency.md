# Dependency

## 라이브러리 버전 확인 
    - https://spring.io/projects/spring-cloud#learn

### 1. Discovery-service
    - spring-web- eureka-client

### 2. Apigateway-service
    - spring-web
    - spring-cloud-gateway
    - eureka-client
    - jjwt 
    - jabx-api
    - Lombok- spring-cloud-starter-config
    - actuator
    - prometheus
    - cloud-bus-amqp
    - jwt io.jsonwebtoken:jjwt-api:0.11.5 
    - runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5' 
    - runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    - spring-cloud-starter-config
    - spring-cloud-starter-bootstrap
    - Spring Boot Starter Actuator
    - spring-cloud-starter-bus-amqp
    - micrometer-registry-prometheus


### 3. User-service
    - spring boot dev tools 
    - Lombok
    - spring web
    - eureka discovery client
    - spring security
    - h2 database
    - JPA
    - validation
    - modelMapper
    - kotlin JPA entity 기본생성자를 위한 org.jetbrains.kotlin.plugin.jpa
    - JPA 를 사용할때 "Proxy Lazy Fetching" 를 위한 클래스 상속 과 function이 상속가능 형태로 변경해주는 id "org.jetbrains.kotlin.plugin.allopen" version "1.6.21"
    - micrometer-registry-prometheus
    - jwt io.jsonwebtoken:jjwt-api:0.11.5 
    - runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    - runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    - spring-cloud-starter-config- 
    - spring-cloud-starter-bootstrap- 
    - Spring Boot Starter Actuator 
    - spring-cloud-starter-bus-amqp 
    - spring-cloud-starter-openfeign 
    - spring-cloud-starter-circuitbreaker-resilience4j 
    - zipkin micrometer-tracing 
    - micrometer-observation 
    - micrometer-tracing-bridge-brave 
    - zipkin-reporter-brave


### 4. Catalog-service
    - Spring Boot Devtools
    - Lombok
    - Spring Web
    - Spring Data JPA
    - Eureka Discovery Client
    - h2 database
    - Kafka


### 5. Order-service
    - Spring Boot Devtools
    - Lombok
    - Spring Web
    - Spring Data JPA
    - Eureka Discovery Client
    - h2 database
    - Kafka
    - zipkin
    - actuator
    - micrometer-tracing
    - micrometer-observation
    - micrometer-tracing-bridge-brave
    - zipkin-reporter-brave
    - micrometer-registry-prometheus
    - spring-cloud-starter-bus-amqp


### 6. Config-service
    - config server
    - Spring Boot Starter Actuator
    - spring-cloud-starter-bus-amqp
