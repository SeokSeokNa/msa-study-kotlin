package io.seok.userservice.domain

import io.seok.userservice.dto.UserDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false , length = 50 , unique = true)
    val email: String,
    @Column(nullable = false , length = 50)
    val name: String,
    @Column(nullable = false , unique = true)
    val userId: String,
    @Column(nullable = false , unique = true)
    var encryptedPwd: String
) {
    companion object {
        fun createUserEntity(userDto: UserDto): UserEntity {
            return UserEntity(
                email = userDto.email,
                name = userDto.name,
                userId = userDto.userId,
                encryptedPwd = userDto.encryptedPwd,
            )
        }
    }
}