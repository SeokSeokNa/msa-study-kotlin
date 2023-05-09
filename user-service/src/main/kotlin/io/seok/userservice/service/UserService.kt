package io.seok.userservice.service

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.dto.UserDto
import io.seok.userservice.vo.ResponseUser
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {

    fun createUser(userDto: UserDto): UserDto

    fun getUserByUserId(userId: String): ResponseUser

    fun getUserByAll(): List<ResponseUser>
}