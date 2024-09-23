package example.com.repository.user

import example.com.data.model.User

interface UserRepository {

    suspend fun createUser(user:User): Boolean

    suspend fun getUserById(id:String):User?

    suspend fun getUserByEmail(email:String): User?

}