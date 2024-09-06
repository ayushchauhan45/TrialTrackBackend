package example.com.data.repository

import example.com.data.model.User

interface UserRepository {

    suspend fun createUser(user:User)

    suspend fun getUserById(id:String):User?

    suspend fun getUserByEmail(email:String): User?

    suspend fun doesPasswordForUserMatch(email:String,enteredPassword:String): Boolean
}