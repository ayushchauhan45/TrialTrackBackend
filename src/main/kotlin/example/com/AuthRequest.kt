package example.com

import kotlinx.serialization.Serializable


@Serializable
data class AuthRequest(
    val email:String,
    val username:String,
    val password:String,
    val role:String
)
