package example.com.data.request

import kotlinx.serialization.Serializable

@Serializable
data class DetailRequest(
    val name:String,
    val age:Int,
    val city:String,
    val state:String,
    val image:String
)
