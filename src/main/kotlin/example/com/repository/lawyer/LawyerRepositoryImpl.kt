package example.com.repository.lawyer

import example.com.data.model.Lawyer
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.`in`

class LawyerRepositoryImpl(
    db:CoroutineDatabase
):LawyerRepository {

    private val dbLawyer = db.getCollection<Lawyer>()

    override suspend fun insertLawyer(lawyer: Lawyer):Boolean {
       return dbLawyer.insertOne(lawyer).wasAcknowledged()
    }

    override suspend fun getLawyerByID(lawyerId: String): Lawyer? {
       return dbLawyer.findOneById(lawyerId)

    }

    override suspend fun getLawyers(lawyerIds:List<String>,page:Int,pageSize:Int): List<Lawyer> {
        return dbLawyer.find(Lawyer::lawyerId `in` lawyerIds )
            .skip(page*pageSize)
            .limit(pageSize)
            .toList()
    }

    override suspend fun getLawyersByType(type: String): List<Lawyer> {
        return dbLawyer.find(Lawyer::type  eq type).toList()
    }
}