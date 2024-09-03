package example.com

import example.com.plugins.*
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

val dbClient = KMongo.createClient("").coroutine
val getDataBase = dbClient.getDatabase("")

fun Application.module() {
    configureSecurity()
    configureSockets()
    configureSerialization()
    configureFrameworks()
    configureMonitoring()
    configureRouting()
}
