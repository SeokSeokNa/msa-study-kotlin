package io.seok.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableDiscoveryClient // 디스커버리 클라이언트 어노테이션을 안써도 dependency만 설정 되어있으면 자동등록됨 ( 명시적으로 보여주기 위해 해당 어노테이션을 설정함 )
class UserServiceApplication {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

fun main(args: Array<String>) {
    runApplication<UserServiceApplication>(*args)
}


