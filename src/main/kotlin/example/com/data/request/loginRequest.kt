package example.com.data.request

import kotlinx.serialization.Serializable

@Serializable
data class loginRequest(
    val email:String,
    val password:String
)
