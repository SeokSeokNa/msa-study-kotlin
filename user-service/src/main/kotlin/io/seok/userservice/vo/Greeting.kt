package io.seok.userservice.vo

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Greeting(
    @Value("\${greeting.message: 없으면 디폴트값 사용}") val message: String
){

}