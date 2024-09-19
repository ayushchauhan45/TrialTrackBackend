package example.com.security.token

interface TokenService {
    fun generateToken(config: tokenConfig, vararg claim:TokenClaims):  String
}