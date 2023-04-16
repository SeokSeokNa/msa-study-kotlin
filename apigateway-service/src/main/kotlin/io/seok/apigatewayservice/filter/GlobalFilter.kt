package io.seok.apigatewayservice.filter

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/*
    모든 요청에대한 필터작업을 하고싶을때
 */
@Component
@Slf4j
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    val logger: Logger = LoggerFactory.getLogger(GlobalFilter::class.java)


    class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    )

    override fun apply(config: Config): GatewayFilter {
        //RxJava를 위한 spring5 의 webflux 기능을 사용
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            //Global Pre Filter
            logger.info("Global Filter baseMessage: {}", config.baseMessage)

            if (config.preLogger) {
                logger.info("Global Filter start: request id -> {}", request.id)
            }

            //Global Post Filter
            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    logger.info("Global POST Filter End: response code -> {}", response.statusCode)
                }
            })
        }
    }


}