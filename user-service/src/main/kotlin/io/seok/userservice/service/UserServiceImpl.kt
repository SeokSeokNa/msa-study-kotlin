package io.seok.userservice.service

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.dto.UserDto
import io.seok.userservice.repository.UserRepository
import io.seok.userservice.vo.ResponseOrder
import io.seok.userservice.vo.ResponseUser
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {


    override fun createUser(userDto: UserDto): UserDto {
        userDto.userId = UUID.randomUUID().toString()
//        val modelMapper = ModelMapper()
//        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT // 전략 설정(딱 맞아떨어지지 않으면 변환 못하게)
//        val userEntity = modelMapper.map(userDto , UserEntity::class.java)
        val userEntity = UserEntity.createUserEntity(userDto)
        userEntity.encryptedPwd = passwordEncoder.encode(userDto.pwd)
        userRepository.save(userEntity)

        return userDto
    }

    override fun getUserByUserId(userId: String): ResponseUser {
        val userEntity: UserEntity =
            userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("User not found")

        val userDto = UserDto.convertUserDto(userEntity)

        val orders: List<ResponseOrder> = ArrayList()
        userDto.orders = orders


        return ResponseUser.createResponseUserFromUserDto(userDto)

    }

    override fun getUserDetailsByEmail(email: String): UserDto {
        val userEntity = userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return UserDto.convertUserDto(userEntity)
    }

    override fun getUserByAll(): List<ResponseUser> {
        return userRepository.findAll()
            .map { userEntity -> ResponseUser.createResponseUserFromEntity(userEntity) }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity: UserEntity = userRepository.findByEmail(username) ?: throw UsernameNotFoundException("")

        return User(
            userEntity.email, userEntity.encryptedPwd,
            true, true, true, true,
            listOf()
        )
    }
}