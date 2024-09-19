package example.com.service

import example.com.data.model.Chat
import example.com.data.model.Message
import example.com.repository.chat.ChatRepository

class ChatService(
    private val chatRepository: ChatRepository
){
    suspend fun getMessageForChat(chatId:String,page:Int,  pageSize:Int):List<Message>{
        return chatRepository.getMessagesFromChat(chatId,page,pageSize)
    }

    suspend fun getChatForUser(ownUserId:String):List<Chat>{
        return chatRepository.getChatsFromUsers(ownUserId)
    }
    suspend fun doesChatBelongToUser(chatId: String,userId:String):Boolean{
        return chatRepository.doesChatBelongToUser(chatId,userId)
    }
}