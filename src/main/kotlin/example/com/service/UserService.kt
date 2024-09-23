package example.com.service

import example.com.data.model.User
import example.com.repository.user.UserRepository

class UserService(private val userRepository: UserRepository) {

    suspend fun createUser(user: User): Boolean{
        return userRepository.createUser(user)
    }

    suspend fun getUserById(id:String): User?{
        return userRepository.getUserById(id)
    }

    suspend fun getUserByEmail(email:String): User?{
        return userRepository.getUserByEmail(email)
    }


}