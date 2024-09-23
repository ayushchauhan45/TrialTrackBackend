package example.com.data.request

import kotlinx.serialization.Serializable

@Serializable
data class LawyerRequest(
    val name:String,
    val type:String,
    val Bio:Long,
    val profilePicture:String,
    val graduation:String,
    val yearOfPractice:String,
    val price:String,
    val successRate:String
)
