package io.seok.userservice.security

import io.seok.userservice.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class WebSecurity(
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val passwordEncoder: BCryptPasswordEncoder, //security 5.7이상 버전부터는 BCryptPasswordEncoder가 bean으로 어딘가 정의만 되어있으면 자동적용된다.(따로 설정하지 않아도된다)
    private val userService: UserService,
    private val env: Environment
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeHttpRequests()
//            .requestMatchers("/users/**").permitAll()
//            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .requestMatchers("/actuator/**").permitAll() //서버 상태 값을 체크할 수 있는 actuator/** 패턴은  모두 통과시키도록 설정
            .requestMatchers("/**").permitAll()
            .and()
            .userDetailsService(userService)
            .addFilter(AuthenticationFilter(authenticationManager(authenticationConfiguration),userService,env))
            .build();
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }



}