package example.com.security.token

data class TokenClaims(
    val name: String,
    val value:String,
    val roleName:String,
    val roleValue:String
)
