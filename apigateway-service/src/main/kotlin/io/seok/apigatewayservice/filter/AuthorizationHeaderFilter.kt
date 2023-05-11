package io.seok.apigatewayservice.filter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.apache.http.HttpHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets
import java.security.Key

@Component
class AuthorizationHeaderFilter(
    val env: Environment
) : AbstractGatewayFilterFactory<CustomFilter.Config>(CustomFilter.Config::class.java) {

    val log: Logger = LoggerFactory.getLogger(AuthorizationHeaderFilter::class.java)

    class Config()
    override fun apply(config: CustomFilter.Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request

            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return@GatewayFilter onError(exchange , "not authorization header" , HttpStatus.UNAUTHORIZED) // return@GatewayFilter  표현은 해당 람다식을 호출한 곳이 GatewayFilter 인데 GatewayFilter의 구현한 람다함수의 리턴값을 onError 으로 한다는뜻
                                                                                                                      // return@a 1 -> 라벨 a 라는곳에 리턴값을 1로 하겠다는뜻
            }

            val bearerToken = request.headers.get(HttpHeaders.AUTHORIZATION)!!.get(0)
            val token = bearerToken.replace("Bearer","")

            if (!isJwtValid(token)) {
                return@GatewayFilter onError(exchange , "JWT token is not valid" , HttpStatus.UNAUTHORIZED)
            }

            //Global Post Filter
            chain.filter(exchange)
        }
    }

    private fun isJwtValid(token: String): Boolean {
        val key: Key = Keys.hmacShaKeyFor(env.getProperty("token.secret")!!.toByteArray(StandardCharsets.UTF_8))


        var returnValue = true;
        var subject: String? = null

        try {
            subject = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).body
                .subject
        } catch (ex: Exception) {
            returnValue = false
        }


        if (subject.isNullOrEmpty()) {
            returnValue = false
        }

        return returnValue
    }

    private fun onError(exchange: ServerWebExchange, errorMsg: String, httpStatus: HttpStatus): Mono<Void> {
        val response = exchange.response
        response.statusCode = httpStatus

        log.error(errorMsg)
        return response.setComplete()
    }
}