package example.com.repository.lawyer

import example.com.data.model.Lawyer
import example.com.util.Constants

interface LawyerRepository {
    suspend fun insertLawyer(lawyer:Lawyer):Boolean

    suspend fun getLawyerByID(lawyerId:String):Lawyer?

    suspend fun getLawyers(lawyerIds:List<String>, page:Int = 0,pageSize:Int = Constants.DEFAULT_PAGE_SIZE):List<Lawyer>

    suspend fun getLawyersByType(type:String):List<Lawyer>
}