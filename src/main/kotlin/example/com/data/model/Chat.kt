package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Chat(
    @BsonId
    val id:String = ObjectId().toString(),
    val lastMessage:Message,
    val users:List<SimpleUser>,
    val timeStamp:Long
)
