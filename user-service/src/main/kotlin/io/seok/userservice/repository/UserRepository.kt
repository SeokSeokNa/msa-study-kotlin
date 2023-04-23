package io.seok.userservice.repository

import io.seok.userservice.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByUserId(userId: String): UserEntity?


}