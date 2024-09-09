package example.com.repository

import example.com.data.model.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepositoryImpl(
     db:CoroutineDatabase
): UserRepository {

    private val user = db.getCollection<User>()
    override suspend fun createUser(user: User):Boolean {
       return this.user.insertOne(user).wasAcknowledged()
    }

    override suspend fun getUserById(id: String): User? {
        return this.user.findOneById(id)
    }

    override suspend fun getUserByEmail(email:String): User? {
      return  this.user.findOne(
           User::email eq email
      )
    }

    override suspend fun doesPasswordForUserMatch(email: String, enteredPassword: String): Boolean {
        val correctEmail = getUserByEmail(email)

        return correctEmail?.password == enteredPassword
    }
}