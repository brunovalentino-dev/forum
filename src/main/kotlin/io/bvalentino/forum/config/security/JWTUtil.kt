package io.bvalentino.forum.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.bvalentino.forum.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val userService: UserService
) {

    @Value("\${jwt.secret}")
    private var secret: String = ""
    private val expiration: Long = 240000L

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        val userAuthorities = authorities.map { it.authority }

        return JWT.create()
            .withSubject(username)
            .withClaim("roles", userAuthorities)
            .withExpiresAt(Date(System.currentTimeMillis() + expiration))
            .sign(Algorithm.HMAC256(secret.toByteArray()))
    }

    fun isValid(providedToken: String): Boolean {
        return try {
            JWT.require(Algorithm.HMAC256(secret.toByteArray())).build().verify(providedToken)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(providedToken: String): Authentication {
        val username = JWT.require(Algorithm.HMAC256(secret.toByteArray()))
            .build()
            .verify(providedToken)
            .subject

        val userDetails = userService.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(username, null, userDetails.authorities)
    }

}