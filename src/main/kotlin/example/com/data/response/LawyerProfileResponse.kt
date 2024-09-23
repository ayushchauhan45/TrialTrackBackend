package example.com.data.response

data class LawyerProfileResponse(
    val lawyerId:String,
    val name:String,
    val type:String,
    val bio:String,
    val profilePicture:String,
    val graduation:String,
    val yearOfPractice:String,
    val fees:String,
    val successRate:String
)