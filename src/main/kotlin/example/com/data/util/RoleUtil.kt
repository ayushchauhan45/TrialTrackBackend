package example.com.data.util

import example.com.plugins.RoleBasedAuthorizationPlugin
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.role(vararg hasAnyRole:String,
                     build:Route.()->Unit){
    install(RoleBasedAuthorizationPlugin){
        roles = hasAnyRole.toSet()
    }
    build()
}