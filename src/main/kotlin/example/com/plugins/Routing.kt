package example.com.plugins


import example.com.routes.*
import example.com.security.hash.HashService
import example.com.security.token.tokenConfig
import example.com.security.token.TokenService
import example.com.service.ClientService
import example.com.service.LawyerService
import example.com.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userService: UserService by inject()
    val hashService: HashService by inject()
    val tokenService: TokenService by inject()
    val tokenConfig: tokenConfig by inject()
    val clientService: ClientService by inject()
    val  lawyerService: LawyerService by inject()
    routing {
        //authenticate
          signUp(userService,hashService)
          loginUser(userService,hashService,tokenService,tokenConfig)
        //user
          userClientDetail(clientService)
        //Lawyer
        lawyerDetail(lawyerService)
        getLawyers(lawyerService)
        getLawyerProfile(lawyerService)
        getLawyersByType(lawyerService)
    }
}
