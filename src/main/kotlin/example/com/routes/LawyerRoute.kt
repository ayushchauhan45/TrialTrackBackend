package example.com.routes


import example.com.data.request.LawyerRequest
import example.com.data.response.BasicApiResponse
import example.com.service.LawyerService
import example.com.util.ApiResponseMessage
import example.com.util.QueryParams
import example.com.util.role
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.lawyerDetail(lawyerService: LawyerService) {
    authenticate {
        role("Lawyer"){
            post("api/lawyer/detail"){
                val requestLawyer = call.receiveOrNull<LawyerRequest>()?:kotlin.run {
                    call.respond(HttpStatusCode.BadRequest,"Bad Request")
                    return@post
                }

              val isAllFieldEntered = requestLawyer.type.isBlank() || requestLawyer.type.isBlank()

                if(isAllFieldEntered){
                    call.respond(HttpStatusCode.BadRequest,"Enter all  the fields")
                    return@post
                }

                 val insertLawyer = lawyerService.insertLawyer(requestLawyer)
                if(!insertLawyer){
                    call.respond(HttpStatusCode.Conflict,"Not added")
                }

                call.respond(HttpStatusCode.OK)


            }
        }
    }
}

fun Route.getLawyerProfile(lawyerService: LawyerService){
    authenticate {
            get("api/lawyer/profile") {
                val lawyerId = call.parameters[QueryParams.PARAM_LAWYER_ID]
                if (lawyerId.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val getLawyerProfile = lawyerService.getLawyerProfile(lawyerId)
                if (getLawyerProfile == null) {
                    call.respond(
                        HttpStatusCode.ExpectationFailed,
                        BasicApiResponse<Unit>(
                            successful = false,
                            message = ApiResponseMessage.USER_NOT_FOUND
                        )
                    )
                    return@get
                }

                call.respond(
                    HttpStatusCode.OK,
                    BasicApiResponse(
                        successful = true,
                        data = getLawyerProfile
                    )
                )
            }
    }
}
fun Route.getLawyers(lawyerService: LawyerService){
    authenticate {
        role("Client") {
            get("api/lawyers") {
                val lawyerIdParams = call.parameters[QueryParams.PARAM_LAWYER_ID]
                if (lawyerIdParams.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val lawyerIds = lawyerIdParams.split(",").map { it.trim() }


                val getLawyers = lawyerService.getLawyers(lawyerIds)
                if (getLawyers.isEmpty()) {
                    call.respond(
                        HttpStatusCode.ExpectationFailed,
                        BasicApiResponse<Unit>(
                            successful = false,
                            message = ApiResponseMessage.USER_NOT_FOUND
                        )
                    )
                    return@get
                }

                call.respond(
                    HttpStatusCode.OK,
                    BasicApiResponse(
                        successful = true,
                        data = getLawyers
                    )
                )
            }
        }
    }
}
fun Route.getLawyersByType(
    lawyerService: LawyerService
){
    authenticate {
        role("Client"){
            get("api/lawyer/{lawyerType}") {
                val type = call.parameters["lawyerType"]
                if (type.isNullOrBlank()){
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val getLawyers = lawyerService.getLawyerByType(type)
                if (getLawyers.isEmpty()) {
                    call.respond(
                        HttpStatusCode.ExpectationFailed,
                        BasicApiResponse<Unit>(
                            successful = false,
                            message = ApiResponseMessage.USER_NOT_FOUND
                        )
                    )
                    return@get
                }

                call.respond(
                    HttpStatusCode.OK,
                    BasicApiResponse(
                        successful = true,
                        data = getLawyers
                    )
                )
            }
        }
    }
}