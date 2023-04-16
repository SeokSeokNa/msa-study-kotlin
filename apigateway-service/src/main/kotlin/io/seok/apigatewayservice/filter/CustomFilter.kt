package io.seok.apigatewayservice.filter

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/*
    라우팅 정보마다 필요한 작업이 있을때 이러한 커스텀 필터 만들어서 사용하기(Global 하지 않음)
 */
@Component
@Slf4j
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {

    val logger: Logger = LoggerFactory.getLogger(CustomFilter::class.java)

    class Config()

    override fun apply(config: Config): GatewayFilter {
        //RxJava를 위한 spring5 의 webflux 기능을 사용
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            //Custom Pre Filter
            logger.info("Custom PRE filter: request id -> {}" , request.getId())

            //Custom Post filter
            //chain.filter(exchange) -> Post filter 추가 작업
            chain.filter(exchange).then(Mono.fromRunnable {
                logger.info("Custom POST filter: res -> {}" , response.getStatusCode())
            })

        }
    }


}