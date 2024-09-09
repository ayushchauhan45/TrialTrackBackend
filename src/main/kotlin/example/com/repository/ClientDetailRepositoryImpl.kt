package example.com.repository

import example.com.data.model.Client
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ClientDetailRepositoryImpl(
     db:  CoroutineDatabase
):ClientDetailRepository {

    private val clientDB  = db.getCollection<Client>()
    override suspend fun enterClientDetail(client: Client):Boolean {
       return this.clientDB.insertOne(client).wasAcknowledged()
    }

    override suspend fun getClientDetailById(id: String): Client? {
        return this.clientDB.findOne(Client::id eq id)
    }
}