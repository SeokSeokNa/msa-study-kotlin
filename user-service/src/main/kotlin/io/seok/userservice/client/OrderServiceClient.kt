package io.seok.userservice.client

import io.seok.userservice.vo.ResponseOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "order-service") // 호출할 마이크로 서비스 name을 표시해야함
interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders_ng") //원하는 API의 엔드포인트 주소를 넣어야함
    fun getOrders(@PathVariable userId: String): ResponseEntity<List<ResponseOrder>>
}