package io.seok.userservice.repository

import io.seok.userservice.domain.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity , Long>{


}