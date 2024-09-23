package example.com.routes

import example.com.security.hash.HashService
import example.com.security.token.TokenClaims
import example.com.security.token.tokenConfig
import example.com.security.token.TokenService
import example.com.data.model.User
import example.com.data.request.UserRequest
import example.com.data.request.loginRequest
import example.com.data.response.AuthResponse
import example.com.security.hash.saltedHash
import example.com.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.signUp(userService: UserService, hashService: HashService ){
        post("user/signup") {
            val createUser = call.receiveOrNull<UserRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val isEmailOrPasswordEntered = createUser.email.isBlank() || createUser.password.isBlank()

            val validPasswordLength = createUser.password.length >= 8

            val passwordContainSpecialCharacter = createUser.password.any { it in "@$!%*#?&" }

            val passwordContainCapitalLetter = createUser.password.any { it.isUpperCase() }


            if (isEmailOrPasswordEntered || !validPasswordLength || !passwordContainSpecialCharacter || !passwordContainCapitalLetter) {
                call.respond(HttpStatusCode.BadRequest, "Invalid Password")
                return@post
            }

            val hash = hashService.generateHash(
                value = createUser.password
            )
            val user = User(
                email = createUser.email,
                role = createUser.role,
                password = hash.hash,
                salt = hash.salt,
            )
            val userInserted = userService.createUser(user)

            if (!userInserted) {
                call.respond(HttpStatusCode.BadRequest, "Unable to enter user")
            }

            call.respond(HttpStatusCode.OK)


        }
}



fun Route.loginUser(userService: UserService,
                    hashService: HashService,
                    tokenService: TokenService,
                    tokenConfig: tokenConfig
 ){

    post("/user/login")
    {
             val request = call.receiveOrNull<loginRequest>() ?: kotlin.run {
                 call.respond(HttpStatusCode.BadRequest,"Not found")
                 return@post
             }

             if (request.email.isBlank() && request.password.isBlank()) {
                 call.respond(HttpStatusCode.BadRequest,"email is blank")
                 return@post
             }


             val emailRegisteredUser = userService.getUserByEmail(request.email)

             if (emailRegisteredUser == null) {
                 call.respond(HttpStatusCode.BadRequest, "Email Not registered")
                 return@post
             }

             val doesPasswordMatch = hashService.verifyHash(
                 value = request.password,
                 saltedHash = saltedHash(
                     hash = emailRegisteredUser.password,
                     salt = emailRegisteredUser.salt,
                 )
             )
             if (!doesPasswordMatch) {
                 call.respond(HttpStatusCode.BadRequest, "Wrong Password")
                 return@post
             }
             val token = tokenService.generateToken(
                 config = tokenConfig,
                 TokenClaims(
                     name = "id",
                     value = emailRegisteredUser.id,
                     roleName = "role",
                     roleValue = emailRegisteredUser.role
                 )
             )

             call.respond(
                 status = HttpStatusCode.OK,
                 message = AuthResponse(
                     token = token
                 )
             )
    }
}






