package example.com.data.request

import kotlinx.serialization.Serializable

@Serializable
data class LawyerRequest(
    val name:String,
    val type:String,
    val bio:String,
    val profilePicture:String,
    val graduation:String,
    val yearOfPractice:String,
    val fees:String,
    val successRate:String
)
