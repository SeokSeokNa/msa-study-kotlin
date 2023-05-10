package io.seok.userservice.dto

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.vo.RequestUser
import io.seok.userservice.vo.ResponseOrder
import java.time.LocalDate
import java.util.Date

data class UserDto(
    val email: String,
    val name: String,
    val pwd: String,
    var userId: String,
    var createdAt: Date,

    var encryptedPwd: String,
    var orders: List<ResponseOrder>
) {

    companion object {
        fun of(requestUser: RequestUser): UserDto {
            return UserDto(
                email =  requestUser.email!!,
                name = requestUser.name!!,
                pwd = requestUser.pwd!!,
                "",
                Date(),
                "",
                ArrayList()
            )
        }

        fun convertUserDto(userEntity: UserEntity): UserDto {
            return UserDto(
                email = userEntity.email,
                name = userEntity.name,
                pwd = userEntity.encryptedPwd,
                userId = userEntity.userId,
                Date(),
                encryptedPwd = userEntity.encryptedPwd,
                ArrayList()
            )
        }
    }
}
