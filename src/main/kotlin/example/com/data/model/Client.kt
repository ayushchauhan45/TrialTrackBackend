package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Client(
    @BsonId
    val id:String =ObjectId().toString(),
    val name:String,
    val age:Int,
    val city:String,
    val state:String,
    val image:String
)
