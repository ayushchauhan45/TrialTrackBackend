package example.com.security.hash

data class saltedHash(
    val hash:String,
    val salt: String
)
