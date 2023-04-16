package io.seok.userservice.controller

import io.seok.userservice.dto.UserDto
import io.seok.userservice.service.UserService
import io.seok.userservice.vo.Greeting
import io.seok.userservice.vo.RequestUser
import io.seok.userservice.vo.ResponseUser
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController(
    private val env: Environment,
    private val greeting: Greeting,
    private val userService: UserService
) {

    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in UserService"
    }

    @GetMapping("/welcome")
    fun welcome(): String? {
        return greeting.message
        //return env.getProperty("c")
    }

    @PostMapping("/users")
    fun createUser(@RequestBody requestUser: RequestUser): ResponseEntity<ResponseUser> {

        val userDto = UserDto.of(requestUser)
        userService.createUser(userDto)

        val responseUser = ResponseUser(userDto.email, userDto.name, userDto.userId)

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser)
    }
}