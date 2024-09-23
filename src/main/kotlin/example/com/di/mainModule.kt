package example.com.di
import example.com.repository.chat.ChatRepository
import example.com.repository.chat.ChatRepositoryImpl
import example.com.repository.client.ClientDetailRepository
import example.com.repository.client.ClientDetailRepositoryImpl
import example.com.repository.lawyer.LawyerRepository
import example.com.repository.lawyer.LawyerRepositoryImpl
import example.com.repository.user.UserRepository
import example.com.repository.user.UserRepositoryImpl
import example.com.security.hash.HashService
import example.com.security.hash.SHA56HashingImpl
import example.com.security.token.TokenService
import example.com.security.token.TokenServiceImpl
import example.com.service.ChatService
import example.com.service.ClientService
import example.com.service.LawyerService
import example.com.service.UserService
import example.com.util.Utils
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module{
    single {
        val dbClient = KMongo.createClient(connectionString = "").coroutine
            dbClient.getDatabase(Utils.DATABASE_NAME)
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<ClientDetailRepository> {
        ClientDetailRepositoryImpl(get())
    }
    single<LawyerRepository> {
        LawyerRepositoryImpl(get())
    }
    single<ChatRepository> {
        ChatRepositoryImpl(get())
    }
    single<TokenService> {
        TokenServiceImpl()
    }
    single<HashService> {
        SHA56HashingImpl()
    }
    single { UserService(get())  }
    single { ClientService(get())  }
    single { LawyerService(get())  }
    single { ChatService(get())  }

}