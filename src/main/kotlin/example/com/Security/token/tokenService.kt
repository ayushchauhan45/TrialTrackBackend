package example.com.Security.token

interface tokenService {
    fun generateToken( config: tokenConfig, vararg claim:tokenClaims):  String
}