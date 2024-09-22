package example.com.service

import example.com.data.model.Lawyer
import example.com.data.request.LawyerRequest
import example.com.data.response.LawyerResponse
import example.com.repository.lawyer.LawyerRepository
import io.ktor.http.*

class LawyerService(
    private val lawyerRepository: LawyerRepository
) {
    suspend fun insertLawyer(request:LawyerRequest):Boolean{
        return lawyerRepository.insertLawyer(
            Lawyer(
                name = request.name,
                type = request.type,
                Bio= request.Bio,
                graduation = request.graduation,
                yearOfPractice= request.yearOfPractice,
                price= request.price ,
                successRate= request.successRate
            )
        )
    }

    suspend fun getLawyerProfile(userId:String): LawyerResponse? {
        val getLawyer = lawyerRepository.getLawyerByID(userId) ?: return null
        return LawyerResponse(
            name = getLawyer.name,
            type = getLawyer.name,
            Bio= getLawyer.Bio,
            graduation = getLawyer.graduation,
            yearOfPractice= getLawyer.yearOfPractice,
            price= getLawyer.price ,
            successRate= getLawyer.successRate
        )
    }


}