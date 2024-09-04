package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Lawyer(
    @BsonId
    val id:String = ObjectId().toString(),
    val name:String,
    val type:String
)
