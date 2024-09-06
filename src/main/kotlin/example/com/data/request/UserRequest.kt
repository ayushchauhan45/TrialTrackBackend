package example.com.data.request

import kotlinx.serialization.Serializable


@Serializable
data class UserRequest(
    val email:String,
    val username:String,
    val password:String,
    val role:String
)
