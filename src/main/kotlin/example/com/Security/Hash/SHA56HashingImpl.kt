package example.com.Security.Hash

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA56HashingImpl:HashService {
    override fun generateHash(value: String, saltLength: Int): saltedHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
        val hexSalt = Hex.encodeHexString(salt)
        val hash =DigestUtils.sha256Hex("$hexSalt$value")
        return saltedHash(
            hash = hash,
            salt = hexSalt
        )
    }

    override fun verifyHash(value: String, saltedHash: saltedHash): Boolean {
       return DigestUtils.sha256Hex(saltedHash.salt + value) == saltedHash.hash
    }
}