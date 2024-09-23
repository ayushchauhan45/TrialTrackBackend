package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Lawyer(
    @BsonId
    val lawyerId:String = ObjectId().toString(),
    val name:String,
    val type:String,
    val profilePicture:String,
    val bio:Long,
    val graduation:String,
    val yearOfPractice:String,
    val price:String,
    val successRate:String
)

