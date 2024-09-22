package example.com.repository.lawyer

import example.com.data.model.Lawyer
import org.litote.kmongo.coroutine.CoroutineDatabase

class LawyerRepositoryImpl(
    db:CoroutineDatabase
):LawyerRepository {

    private val dbLawyer = db.getCollection<Lawyer>()

    override suspend fun insertLawyer(lawyer: Lawyer):Boolean {
       return dbLawyer.insertOne(lawyer).wasAcknowledged()
    }

    override suspend fun getLawyerByID(lawyerId: String): Lawyer? {
        val lawyer = dbLawyer.findOneById(lawyerId)
        return lawyer
    }

    override suspend fun getLawyers(): List<Lawyer> {
        TODO("Not yet implemented")
    }

    override suspend fun getLawyersByType(type: String): List<Lawyer> {
        TODO("Not yet implemented")
    }
}