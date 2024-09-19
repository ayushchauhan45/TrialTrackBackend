package example.com.routes

import example.com.service.ChatService
import example.com.util.Constants
import example.com.util.QueryParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getMessageForChat(chatService: ChatService){
    authenticate {
        get("api/chat/message") {
            val chatId  = call.parameters[QueryParams.PARAM_CHAT_ID]?:kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val page =   call.parameters[QueryParams.PARAM_PAGE]?.toIntOrNull()?: 0
            val pageSize = call.parameters[QueryParams.PARAM_PAGE_SIZE]?.toIntOrNull() ?: Constants.DEFAULT_PAGE_SIZE

            if(!chatService.doesChatBelongToUser(chatId, call.userId)){
                  call.respond(HttpStatusCode.Forbidden)
                return@get
            }
              val messages = chatService.getMessageForChat(chatId,page,pageSize)
            call.respond(HttpStatusCode.OK,messages)
        }
    }

}


fun Route.getChatsForUser(chatService: ChatService){
    authenticate {
        get {
            val chats = chatService.getChatForUser(call.userId)
            call.respond(HttpStatusCode.OK,chats)
        }
    }
}