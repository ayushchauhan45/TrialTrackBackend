package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class Appointment(
    @BsonId
    val id:String = ObjectId().toString(),
    val date:LocalDate,
    val day:DayOfWeek,
    val time:LocalTime,
    val approval:Boolean?
)
