package example.com.Security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class tokenServiceImpl:tokenService {
    override fun generateToken(config: tokenConfig, vararg claim: TokenClaims, role:String): String {
          var token  = JWT.create()
              .withAudience(config.audience)
              .withIssuer(config.issuer)
              .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))

        claim.forEach {claim->
              token = token.withClaim(claim.name,claim.value).withClaim("role", role)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }
}