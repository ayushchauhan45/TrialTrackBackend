package example.com.security.hash

interface HashService {

    fun generateHash(value: String, saltLength:Int = 32) : saltedHash

    fun verifyHash(value: String, saltedHash: saltedHash): Boolean


}