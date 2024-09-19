package example.com.plugins

import example.com.repository.client.ClientDetailRepository
import example.com.repository.user.UserRepository
import example.com.routes.loginUser
import example.com.routes.signUp
import example.com.routes.userDetail
import example.com.security.hash.HashService
import example.com.security.token.tokenConfig
import example.com.security.token.TokenService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(userRepository: UserRepository,
                                 hashService: HashService,
                                 tokenService: TokenService,
                                 tokenConfig: tokenConfig,
                                 clientDetailRepository: ClientDetailRepository
) {
    routing {
          signUp(userRepository,tokenService,tokenConfig,hashService)
          loginUser(userRepository,hashService,tokenService,tokenConfig)
          userDetail(clientDetailRepository)
    }
}
