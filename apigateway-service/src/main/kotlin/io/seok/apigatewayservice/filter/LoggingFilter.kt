package io.seok.apigatewayservice.filter

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/*
    라우팅 정보마다 필요한 작업이 있을때 이러한 커스텀 필터 만들어서 사용하기(Global 하지 않음)
 */
@Component
@Slf4j
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {

    val logger: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)


    class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    )

    override fun apply(config: Config): GatewayFilter {
        /*   //RxJava를 위한 spring5 의 webflux 기능을 사용
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
           }*/


        //kotlin에서 OrderedGatewayFilter를 통해 GatewayFilter 객체를 얻는 방법
        var filter: GatewayFilter = OrderedGatewayFilter({ exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            //Global Pre Filter
            logger.info("Logging Filter baseMessage: {}", config.baseMessage)

            if (config.preLogger) {
                logger.info("Logging Pre Filter: request id -> {}", request.id)
            }

            //Global Post Filter
            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    logger.info("Logging POST Filter: response code -> {}", response.statusCode)
                }
            })
        }, Ordered.LOWEST_PRECEDENCE) //Ordered 는 필터 순서를 정하는 값(가장 먼저 또는 가장 나중용


        return filter
    }


}