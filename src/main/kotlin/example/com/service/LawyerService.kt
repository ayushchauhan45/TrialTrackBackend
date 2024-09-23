package example.com.service

import example.com.data.model.Lawyer
import example.com.data.request.LawyerRequest
import example.com.data.response.LawyerProfileResponse
import example.com.data.response.LawyerResponse
import example.com.repository.lawyer.LawyerRepository

class LawyerService(
    private val lawyerRepository: LawyerRepository
) {
    suspend fun insertLawyer(request:LawyerRequest):Boolean{
        return lawyerRepository.insertLawyer(
            Lawyer(
                name = request.name,
                type = request.type,
                bio= request.bio,
                profilePicture = request.profilePicture,
                graduation = request.graduation,
                yearOfPractice= request.yearOfPractice,
                fees= request.fees ,
                successRate= request.successRate
            )
        )
    }


    suspend fun getLawyers(lawyerIds:List<String>,):List<LawyerResponse>{
        val getLawyers =  lawyerRepository.getLawyers(lawyerIds)
        return getLawyers.map { lawyer->
       LawyerResponse(
           lawyerId = lawyer.lawyerId,
           name = lawyer.name ,
           bio = lawyer.bio,
           type = lawyer.type,
           profilePicture = lawyer.type,
           fees = lawyer.fees

       )
        }
    }

    suspend fun getLawyerProfile(lawyerId:String): LawyerProfileResponse? {
        val getLawyer = lawyerRepository.getLawyerByID(lawyerId) ?: return null
        return LawyerProfileResponse(
            lawyerId = getLawyer.lawyerId,
            name = getLawyer.name,
            type = getLawyer.name,
            bio= getLawyer.bio,
            profilePicture = getLawyer.profilePicture,
            graduation = getLawyer.graduation,
            yearOfPractice= getLawyer.yearOfPractice,
            fees= getLawyer.fees ,
            successRate= getLawyer.successRate
        )
    }


}