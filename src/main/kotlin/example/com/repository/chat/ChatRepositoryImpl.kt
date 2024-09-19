package example.com.repository.chat

import example.com.data.model.*
import org.litote.kmongo.contains
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ChatRepositoryImpl(
    db:CoroutineDatabase
):ChatRepository {
    private val chat = db.getCollection<Chat>()
    private val user = db.getCollection<Client>()
    private val message = db.getCollection<Message>()

    override suspend fun getMessagesFromChat(chatId: String, page:Int, pageSize: Int): List<Message> {
        return message.find(Message::chatId eq chatId)
            .skip(page*pageSize)
            .limit(pageSize)
            .descendingSort(Message::timeStamp)
            .toList()
    }

    override suspend fun getChatsFromUsers(userId: String): List<Chat> {

        val user  = user.findOneById(userId) ?: return emptyList()
        val simpleUser = SimpleUser(
            id = user.id,
            profilePicture = user.image,
            username = user.name
        )
        return chat.find(Chat::users contains simpleUser)
            .descendingSort(Chat::timeStamp)
            .toList()

    }

    override suspend fun doesChatBelongToUser(chatId: String, userId: String): Boolean {
        return chat.findOneById(chatId)?.users?.any { it.id == userId } == true
    }
}