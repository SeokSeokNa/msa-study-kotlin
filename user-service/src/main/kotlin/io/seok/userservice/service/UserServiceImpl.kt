package io.seok.userservice.service

import io.seok.userservice.domain.UserEntity
import io.seok.userservice.dto.UserDto
import io.seok.userservice.repository.UserRepository
import io.seok.userservice.vo.ResponseOrder
import io.seok.userservice.vo.ResponseUser
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*
import kotlin.collections.ArrayList

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val restTemplate: RestTemplate,
    private val env: Environment
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

        //1. orderService와 통신없이 빈 리스트 넘기는방식
//        val orderList: List<ResponseOrder> = ArrayList()

        //2. RestTemplate로 orderService와 통신하여 리스트 넘기기
        val orderUrl = String.format(env.getProperty("order_service.url")!!,userId) //user-service.yml 환경설정 파일에서 가져온 url 정보에 path variable 데이터로 userId가 필요하기에 string.format을 사용
        val orderListResponse = restTemplate.exchange(
            orderUrl,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<ResponseOrder>>() {})
        val orderList = orderListResponse.body ?: ArrayList()
        userDto.orders = orderList

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