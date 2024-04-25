package io.bvalentino.forum.config.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getHeader("Authorization")?.let {
            token -> token.substring(7, token.length)
        }
        ?.also { providedToken ->
            if (jwtUtil.isValid(providedToken)) {
                SecurityContextHolder.getContext().authentication = jwtUtil.getAuthentication(providedToken)
            }
        }

        filterChain.doFilter(request, response)
    }

}
