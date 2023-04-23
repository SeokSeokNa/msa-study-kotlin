package io.seok.userservice.security

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class WebSecurity {


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/users/**").permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().permitAll()
            .and()
            .build();
    }


}