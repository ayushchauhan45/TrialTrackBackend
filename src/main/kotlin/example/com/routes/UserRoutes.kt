package example.com.routes

import example.com.data.model.Client
import example.com.data.request.DetailRequest
import example.com.repository.client.ClientDetailRepository
import example.com.util.role
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userDetail(clientDetailRepository: ClientDetailRepository){
    authenticate {
        role("Client") {
            post("/user/client/detail") {
                val detailRequest = call.receiveOrNull<DetailRequest>() ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                val isAllFieldEntered =
                    detailRequest.name.isBlank() || detailRequest.city.isBlank() || detailRequest.state.isBlank() || detailRequest.age.toString()
                        .isBlank()

                if (isAllFieldEntered) {
                    call.respond(HttpStatusCode.BadRequest, "Enter the details")
                    return@post
                }
                val client = Client(
                    name = detailRequest.name,
                    age = detailRequest.age,
                    city = detailRequest.city,
                    state = detailRequest.state,
                    image = detailRequest.image
                )
                val enterDetail = clientDetailRepository.enterClientDetail(client)

                if (!enterDetail) {
                    call.respond(HttpStatusCode.BadRequest, "Detail Not Entered......try again")
                    return@post
                }
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}