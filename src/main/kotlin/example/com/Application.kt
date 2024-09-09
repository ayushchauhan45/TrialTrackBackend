package example.com

import example.com.DI.mainModule
import example.com.Security.token.tokenConfig
import example.com.data.util.Utils
import example.com.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}


fun Application.module() {

    val config = tokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L *1000L* 60L*60L*24L,
        secret = System.getenv("JWT_SECRET")
    )


    configureSecurity(config)
    configureSockets()
    configureSerialization()
    configureFrameworks()
    configureMonitoring()
    configureRouting()

    install(Koin){
        modules(mainModule)
    }

}
