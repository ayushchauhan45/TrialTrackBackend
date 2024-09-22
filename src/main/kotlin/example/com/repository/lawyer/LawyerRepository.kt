package example.com.repository.lawyer

import example.com.data.model.Lawyer

interface LawyerRepository {
    suspend fun insertLawyer(lawyer:Lawyer):Boolean

    suspend fun getLawyerByID(lawyerId:String):Lawyer?

    suspend fun getLawyers():List<Lawyer>

    suspend fun getLawyersByType(type:String):List<Lawyer>
}