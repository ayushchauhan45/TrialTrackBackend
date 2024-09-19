package example.com.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class TokenServiceImpl:TokenService {
    override fun generateToken(config: tokenConfig, vararg claim: TokenClaims): String {
          var token  = JWT.create()
              .withAudience(config.audience)
              .withIssuer(config.issuer)
              .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))

        claim.forEach { claim->
              token = token.withClaim(claim.name,claim.value).withClaim(claim.roleName, claim.roleValue)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }
}