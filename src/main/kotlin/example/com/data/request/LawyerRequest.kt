package example.com.data.request

import kotlinx.serialization.Serializable

@Serializable
data class LawyerRequest(
    val name:String,
    val type:String
)
