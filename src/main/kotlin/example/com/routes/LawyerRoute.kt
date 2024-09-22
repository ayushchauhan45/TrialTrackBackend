package example.com.routes

import example.com.data.model.Lawyer
import example.com.data.request.LawyerRequest
import example.com.repository.lawyer.LawyerRepository
import example.com.service.LawyerService
import example.com.util.role
import io.ktor.client.engine.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.lawyerDetail(lawyerService: LawyerService) {
    authenticate {
        role("Lawyer"){
            post("lawyer/detail"){
                val requestLawyer = call.receiveOrNull<LawyerRequest>()?:kotlin.run {
                    call.respond(HttpStatusCode.BadRequest,"Bad Request")
                    return@post
                }

              val isAllFieldEntered = requestLawyer.type.isBlank() || requestLawyer.type.isBlank()

                if(isAllFieldEntered){
                    call.respond(HttpStatusCode.BadRequest,"Enter all  the fields")
                    return@post
                }

                val lawyer = Lawyer(
                    name = requestLawyer.name,
                    type = requestLawyer.type
                )
                 val insertLawyer = lawyerService.insertLawyer(lawyer)
                if(!insertLawyer){
                    call.respond(HttpStatusCode.Conflict,"Not added")
                }

                call.respond(HttpStatusCode.OK)


            }
        }
    }

}