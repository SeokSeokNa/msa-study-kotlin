package io.seok.userservice

import feign.Logger
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import io.seok.userservice.error.FeignErrorDecoder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.client.RestTemplate


@SpringBootApplication
@EnableDiscoveryClient // 디스커버리 클라이언트 어노테이션을 안써도 dependency만 설정 되어있으면 자동등록됨 ( 명시적으로 보여주기 위해 해당 어노테이션을 설정함 )
@EnableFeignClients // FeignClient 를 사용하기 위함
class UserServiceApplication {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @LoadBalanced //다른 마이크로 서비스들과 통신시 IP주소체계(127.0.0.1:8000)를 사용하지 않고 마이크로 서비스 name 체계로 url을 구성하고 싶을때 사용(http://order-service/orders 이런식으로)
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean //Feign Client 를 이용한 통신 사용시 로그를 출력하기 위한 Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean// Feign client Exception 처리를 위한 클래스 bean 등록
    fun getFeignErrorDecoder(): FeignErrorDecoder {
        return FeignErrorDecoder()
    }

    //@Timed 라는 어노테이션을 사용하려면 TimedAspect 타입의 bean을 등록해야함!!!
    @Bean
    fun timedAspect(meterRegistry: MeterRegistry?): TimedAspect? {
        return TimedAspect(meterRegistry!!)
    }
}

fun main(args: Array<String>) {
    runApplication<UserServiceApplication>(*args)
}


