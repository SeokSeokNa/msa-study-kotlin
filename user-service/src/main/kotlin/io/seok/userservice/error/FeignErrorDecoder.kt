package io.seok.userservice.error

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException


class FeignErrorDecoder : ErrorDecoder {

    @Autowired
    private lateinit var env: Environment

    override fun decode(methodKey: String?, response: Response?): Exception? {
        when (response!!.status()) {
            400 -> java.lang.RuntimeException("")
            404 -> if (methodKey!!.contains("getOrders")) {
                return ResponseStatusException(HttpStatusCode.valueOf(response.status()), env.getProperty("order_service.exception.order_is_empty"))
            }
            else -> Exception(response.reason())
        }

        return null
    }
}