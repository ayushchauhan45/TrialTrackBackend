package example.com.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

class PluginConfiguration{
    var roles: Set<String> = emptySet()
}

val RoleBasedAuthorizationPlugin = createRouteScopedPlugin(
    name = "RbacPlugin",
    createConfiguration = ::PluginConfiguration
){
    val roles = pluginConfig.roles

    pluginConfig.apply {
        on(AuthenticationChecked){call ->
            val tokenRole = getRoleFromToken(call)

            val authorized = roles.contains(tokenRole)

            if(!authorized){
                println("User does not have any of th following role : $roles")
                call.respond(HttpStatusCode.Forbidden,"$roles nhi h isme")
            }
        }
    }
}

private fun getRoleFromToken(call:ApplicationCall):String? =
    call.principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("role")
        ?.asString()

