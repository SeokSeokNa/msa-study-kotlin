package io.seok.apigatewayservice

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

/*
    기존 nexfix zuul 에서는 동기방식으로 동작했으나
    spring-cloud-gateway 에서는 비동기 방식을 채택하여 동작시 tomcat이 아닌 netty 웹 서버로 동작한다!
 */
@SpringBootApplication
class ApigatewayServiceApplication

fun main(args: Array<String>) {
    runApplication<ApigatewayServiceApplication>(*args)
}

@Bean
fun httpTraceRepository(): HttpExchangeRepository {
    return InMemoryHttpExchangeRepository()
}
