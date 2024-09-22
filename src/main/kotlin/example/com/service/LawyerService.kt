package example.com.service

import example.com.data.model.Lawyer
import example.com.repository.lawyer.LawyerRepository

class LawyerService(
    private val lawyerRepository: LawyerRepository
) {
    suspend fun insertLawyer(lawyer: Lawyer):Boolean{
        return lawyerRepository.insertLawyer(lawyer)
    }



}