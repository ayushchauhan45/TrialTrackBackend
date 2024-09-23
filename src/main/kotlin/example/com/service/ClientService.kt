package example.com.service

import example.com.data.model.Client
import example.com.repository.client.ClientDetailRepository

class ClientService(
    private val clientDetailRepository: ClientDetailRepository
){
    suspend fun enterClientDetail(client: Client): Boolean{
        return clientDetailRepository.enterClientDetail(client)
    }

    suspend fun getClientDetailById(id:String): Client?{
        return clientDetailRepository.getClientDetailById(id)
    }
}
