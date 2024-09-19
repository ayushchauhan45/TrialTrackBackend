package example.com.repository.chat

import example.com.data.model.Chat
import example.com.data.model.Message

interface ChatRepository {

     suspend fun getMessagesFromChat(chatId:String, page:Int, pageSize:Int):List<Message>

     suspend fun getChatsFromUsers(userId:String):List<Chat>

     suspend fun doesChatBelongToUser(chatId: String,userId: String):Boolean

}