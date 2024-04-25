package io.bvalentino.forum.config.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.bvalentino.forum.dto.UserCredentials
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val(username, password) = jacksonObjectMapper().readValue(request?.inputStream, UserCredentials::class.java)

        return authManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as APIUserDetails)
        val token = jwtUtil.generateToken(user.username, user.authorities)

        response?.addHeader("Authorization", "$token")
    }

}
