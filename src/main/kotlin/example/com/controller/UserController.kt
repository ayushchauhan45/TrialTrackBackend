package example.com.controller

import example.com.data.model.User

interface UserController {

    suspend fun createUser(user:User)


    suspend fun getUserByUsername(username:String): User?


}