package io.seok.userservice.vo

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class RequestLogin(

    @NotNull(message = "Email cannot be null")
    @Size(min = 2 , message = "Email not be less than two characters")
    @Email
    val email: String,

    @NotNull(message = "Password cannot be null")
    @Size(min = 2 , message = "Password must be equals or grater than 8 characters")
    val password: String
) {
}