package io.seok.userservice.service

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.dto.UserDto
import io.seok.userservice.repository.UserRepository
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {


    override fun createUser(userDto: UserDto): UserDto {
        userDto.userId = UUID.randomUUID().toString()
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT // 전략 설정(딱 맞아떨어지지 않으면 변환 못하게)
//        val userEntity = modelMapper.map(userDto , UserEntity::class.java)
        val userEntity = UserEntity.createUserEntity(userDto)
        userEntity.encryptedPwd = "encrypted_password"
        userRepository.save(userEntity)

        return userDto
    }
}