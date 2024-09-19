package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Message(
    @BsonId
    val id:String = ObjectId().toString(),
    val fromId:String,
    val toId:String,
    val text:String,
    val timeStamp:Long,
    val chatId:String
)
