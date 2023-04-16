package io.seok.userservice.dto

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.vo.RequestUser
import java.time.LocalDate
import java.util.Date

data class UserDto(
    val email: String,
    val name: String,
    val pwd: String,
    var userId: String,
    var createdAt: Date,

    var encryptedPwd: String
) {

    companion object {
        fun of(requestUser: RequestUser): UserDto {
            return UserDto(
                email =  requestUser.email!!,
                name = requestUser.name!!,
                pwd = requestUser.pwd!!,
                "",
                Date(),
                ""
            )
        }
    }
}
