package example.com.routes

import example.com.Security.Hash.HashService
import example.com.Security.token.TokenClaims
import example.com.Security.token.tokenConfig
import example.com.Security.token.tokenService
import example.com.data.model.Client
import example.com.data.model.User
import example.com.data.request.DetailRequest
import example.com.repository.UserRepository
import example.com.data.request.UserRequest
import example.com.data.request.loginRequest
import example.com.data.response.AuthResponse
import example.com.data.util.role
import example.com.repository.ClientDetailRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.signUp(){
   val  userRepository : UserRepository by inject()
   val hashService: HashService by inject()

    role("Client", "Lawyer") {

        post("/user/signup") {
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
            val userInserted = userRepository.createUser(user)

            if (!userInserted) {
                call.respond(HttpStatusCode.BadRequest, "Unable to enter user")
            }

            call.respond(HttpStatusCode.OK)

        }
    }
}



fun Route.loginUser(){
    val userRepository: UserRepository by inject()
    val  hashService: HashService by inject()
    val tokenService: tokenService by inject()
    val tokenConfig: tokenConfig by inject()

    role("Client", "Lawyer") {
        post("/user/login")
        {
            val request = call.receiveOrNull<loginRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if (request.email.isBlank() && request.password.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }


            val isUserEmailRegistered = userRepository.getUserByEmail(request.email)

            if (isUserEmailRegistered == null) {
                call.respond(HttpStatusCode.BadRequest, "Email Not registered")
                return@post
            }

            val doesPasswordMatch = userRepository.doesPasswordForUserMatch(request.email, request.password)


            if (!doesPasswordMatch) {
                call.respond(HttpStatusCode.BadRequest, "Wrong Password")
                return@post
            }
            val token = tokenService.generateToken(
                config = tokenConfig,
                TokenClaims(
                    name = "",
                    value = isUserEmailRegistered.id
                ),
                role = isUserEmailRegistered.role
            )

            call.respond(
                status = HttpStatusCode.OK,
                message = AuthResponse(
                    token = token
                )
            )

        }
    }
}

fun Route.userDetail(){
    val clientDetailRepository:ClientDetailRepository by inject()
    role("Client"){
        post("/user/client/detail"){
            val detailRequest = call.receiveOrNull<DetailRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isAllFieldEntered = detailRequest.name.isBlank()||detailRequest.city.isBlank()||detailRequest.state.isBlank()||detailRequest.age.toString().isBlank()

            if (isAllFieldEntered){
                call.respond(HttpStatusCode.BadRequest,"Enter the details")
                return@post
            }
            val client = Client(
                name= detailRequest.name,
                age = detailRequest.age ,
                city = detailRequest.city ,
                state = detailRequest.state,
                image = detailRequest.image
            )
            val enterDetail = clientDetailRepository.enterClientDetail(client)

            if (!enterDetail){
                call.respond(HttpStatusCode.BadRequest,"Detail Not Entered......try again")
                return@post
            }
        }
    }
}




