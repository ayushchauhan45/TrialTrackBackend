package example.com.plugins

import example.com.repository.client.ClientDetailRepository
import example.com.repository.user.UserRepository
import example.com.routes.*
import example.com.security.hash.HashService
import example.com.security.token.tokenConfig
import example.com.security.token.TokenService
import example.com.service.ClientService
import example.com.service.LawyerService
import example.com.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(userService: UserService,
                                 hashService: HashService,
                                 tokenService: TokenService,
                                 tokenConfig: tokenConfig,
                                 clientService: ClientService,
                                 lawyerService: LawyerService
) {
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
