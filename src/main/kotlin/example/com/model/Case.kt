package example.com.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Case(
    @BsonId
    val id:String = ObjectId().toString(),
    val caseId: String,
    val caseType:String,
    val caseDescription:Long
)
