package io.seok.userservice.vo

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

//https://beaniejoy.tistory.com/72 참조
data class RequestUser(

    @field:NotNull(message = "Email cannot be null")
    @field:Size(min = 2 , message = "Email not be less than two characters")
    @field:Email
    val email: String?,

    @field:NotNull(message = "Name cannot be null")
    @field:Size(min = 2 , message = "Name not be less than two characters")
    val name: String?,

    @field:NotNull(message = "Password cannot be null")
    @field:Size(min = 8 , message = "Password must be equals or grater than 8 characters")
    val pwd: String?
) {

}
