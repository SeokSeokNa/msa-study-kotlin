package io.seok.userservice.service

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.dto.UserDto
import io.seok.userservice.vo.ResponseUser

interface UserService {

    fun createUser(userDto: UserDto): UserDto

    fun getUserByUserId(userId: String): ResponseUser

    fun getUserByAll(): List<ResponseUser>
}