package example.com.repository.client

import example.com.data.model.Client

interface ClientDetailRepository {

    suspend fun enterClientDetail(client: Client): Boolean

    suspend fun getClientDetailById(id:String):Client?

}