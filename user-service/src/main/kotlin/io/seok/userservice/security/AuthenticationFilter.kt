package io.seok.userservice.security

import io.seok.userservice.dto.UserDto
import io.seok.userservice.service.UserService
import io.seok.userservice.util.mapperUtil
import io.seok.userservice.vo.RequestLogin
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException

class AuthenticationFilter(
    authenticationManager: AuthenticationManager?,
    private val userService: UserService,
    private val env: Environment
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    private val log = LoggerFactory.getLogger(AuthenticationFilter::class.java)

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {

            val creds = mapperUtil.readValue(request.inputStream, RequestLogin::class.java)
            return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    creds.email,
                    creds.password,
                    listOf()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

        return super.attemptAuthentication(request, response)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
//        super.successfulAuthentication(request, response, chain, authResult)
        val username = (authResult?.principal as User).username
        val userDto: UserDto = userService.getUserDetailsByEmail(username)
    }
}