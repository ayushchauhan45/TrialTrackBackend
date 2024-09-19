package example.com.security.token

data class tokenConfig(
    val issuer:String,
    val audience:String,
    val expiresIn:Long,
    val secret:String
)
