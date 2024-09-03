package example.com.controller

import example.com.data.model.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserControllerImpl(
     db:CoroutineDatabase
):UserController {

    private val user = db.getCollection<User>()
    override suspend fun createUser(user: User) {
        this.user.insertOne(user).wasAcknowledged()
    }

    override suspend fun getUserByUsername(username:String): User? {
      return  this.user.findOne(
           User ::username eq username
       )
    }
}