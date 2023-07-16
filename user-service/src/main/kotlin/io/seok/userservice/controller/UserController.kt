package io.seok.userservice.controller

import io.micrometer.core.annotation.Timed
import io.micrometer.observation.annotation.Observed
import io.seok.userservice.dto.UserDto
import io.seok.userservice.service.UserService
import io.seok.userservice.vo.Greeting
import io.seok.userservice.vo.RequestUser
import io.seok.userservice.vo.ResponseUser
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val env: Environment,
    private val greeting: Greeting,
    private val userService: UserService
) {

    @Timed(value = "users.status", longTask = true) //매트릭 중에 메소드의 실행시간을 수집하기 위한 어노테이션(value 에는 매트릭을 구별할 수 있는 이름을 넣으면된다 , 즉 이 메소드의 실행시간이 얼마나 걸렷는지 구별할 수 있는 고유값)
    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in User Service" +
                ", port(local.server.port)= ${env.getProperty("local.server.port")}" +//"local.server.port"는 랜덤포트로 할당된 값을 가져올수있게 하는것
                ", port(server.port)= ${env.getProperty("server.port")}" +
                ", token secret= ${env.getProperty("token.secret")}" +
                ", token expiration time= ${env.getProperty("token.expiration_time")}"
    }
    @Timed(value = "users.welcome", longTask = true)
    @GetMapping("/welcome")
    fun welcome(): String? {
        return greeting.message
        //return env.getProperty("c")
    }

    @PostMapping("/users")
    fun createUser(@RequestBody requestUser: RequestUser): ResponseEntity<ResponseUser> {

        val userDto = UserDto.of(requestUser)
        userService.createUser(userDto)

        val responseUser = ResponseUser(userDto.email, userDto.name, userDto.userId , userDto.orders)

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser)
    }

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<ResponseUser>> {
        val userList = userService.getUserByAll()
        return ResponseEntity.status(HttpStatus.OK).body(userList)
    }

    @GetMapping("/users/{userId}")
    fun getUsers(@PathVariable userId: String): ResponseEntity<ResponseUser> {
        val findUser = userService.getUserByUserId(userId)

        return ResponseEntity.status(HttpStatus.OK).body(findUser)
    }
}