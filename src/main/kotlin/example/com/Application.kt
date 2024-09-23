package example.com

import example.com.di.mainModule
import example.com.util.Utils
import example.com.security.token.tokenConfig
import example.com.plugins.*
import example.com.repository.client.ClientDetailRepositoryImpl
import example.com.repository.lawyer.LawyerRepositoryImpl
import example.com.repository.user.UserRepositoryImpl
import example.com.security.hash.SHA56HashingImpl
import example.com.security.token.TokenServiceImpl
import example.com.service.ClientService
import example.com.service.LawyerService
import example.com.service.UserService
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}


fun Application.module() {

    install(Koin) {
        modules(mainModule)
    }

    val tokenConfig = tokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L *1000L* 60L*60L*24L,
        secret = System.getenv("JWT_SECRET")
    )




    configureSecurity(tokenConfig)
    configureSockets()
    configureSerialization()
    configureFrameworks()
    configureMonitoring()
    configureRouting()



}
