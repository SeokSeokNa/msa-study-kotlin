package io.seok.userservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import io.seok.userservice.domain.UserEntity
import io.seok.userservice.dto.UserDto

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseUser(
     val email: String,
     val name: String,
     val userId: String,

     var responseOrder: List<ResponseOrder> = ArrayList<ResponseOrder>()
) {
     companion object {
          fun createResponseUserFromEntity(userEntity: UserEntity): ResponseUser {
               return ResponseUser(
                    email = userEntity.email,
                    name = userEntity.name,
                    userId = userEntity.userId
               )
          }

          fun createResponseUserFromUserDto(userDto: UserDto): ResponseUser {
               return ResponseUser(
                    email = userDto.email,
                    name = userDto.name,
                    userId = userDto.userId,
                    responseOrder = userDto.orders
               )
          }
     }
}
