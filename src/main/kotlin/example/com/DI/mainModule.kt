package example.com.DI
import example.com.data.util.Utils
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module{
    single {
        val dbClient = KMongo.createClient("").coroutine.getDatabase(Utils.DATABASE_NAME)

    }

}