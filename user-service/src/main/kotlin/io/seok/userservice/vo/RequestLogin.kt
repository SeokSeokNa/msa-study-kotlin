package io.seok.userservice.vo

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class RequestLogin(

//    @JsonProperty("email")
    @NotNull(message = "Email cannot be null")
    @Size(min = 2 , message = "Email not be less than two characters")
    @Email
    val email: String,

//    @JsonProperty("password")
    @NotNull(message = "Password cannot be null")
    @Size(min = 2 , message = "Password must be equals or grater than 8 characters")
    val password: String
) {
}