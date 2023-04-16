package io.seok.userservice.service

import io.seok.userservice.dto.UserDto

interface UserService {

    fun createUser(userDto: UserDto): UserDto
}