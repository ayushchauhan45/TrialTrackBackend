package example.com

import example.com.util.Utils
import example.com.security.token.tokenConfig
import example.com.plugins.*
import example.com.repository.client.ClientDetailRepositoryImpl
import example.com.repository.user.UserRepositoryImpl
import example.com.security.hash.SHA56HashingImpl
import example.com.security.token.TokenServiceImpl
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}


fun Application.module() {

    val dbClient = KMongo.createClient(connectionString = "mongodb+srv://ayushchauhan:ayushchauhan@ayush.ulpcu.mongodb.net/").coroutine.getDatabase(
        Utils.DATABASE_NAME)

    val tokenConfig = tokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L *1000L* 60L*60L*24L,
        secret = System.getenv("JWT_SECRET")
    )
 val userDataRepository = UserRepositoryImpl(dbClient)
 val tokenService = TokenServiceImpl()
 val hashing = SHA56HashingImpl()
 val clientDetailRepository = ClientDetailRepositoryImpl(dbClient)


    configureSecurity(tokenConfig)
    configureSockets()
    configureSerialization()
    configureFrameworks()
    configureMonitoring()
    configureRouting(userDataRepository,hashing,tokenService,tokenConfig,clientDetailRepository)



}
