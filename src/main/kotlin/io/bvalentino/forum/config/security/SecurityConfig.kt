package io.bvalentino.forum.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtil: JWTUtil
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity, config: AuthenticationConfiguration): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            authorizeHttpRequests {
                authorize(HttpMethod.POST,"/login", permitAll)
                authorize(HttpMethod.GET, "/swagger-ui/*", permitAll)
                authorize(HttpMethod.GET, "/swagger-ui.html", permitAll)
                authorize(HttpMethod.GET, "/v3/api-docs/**", permitAll)
                authorize(HttpMethod.GET, "/webjars/swagger-ui/**", permitAll)
                authorize("/topics", hasAuthority("READ_WRITE"))
                authorize("/answers", hasAuthority("READ_WRITE"))
                authorize("/reports", hasAuthority("ADMIN"))
                authorize(anyRequest, authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JWTLoginFilter(authManager = config.authenticationManager, jwtUtil = jwtUtil)
            )
            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JWTAuthenticationFilter(jwtUtil = jwtUtil)
            )
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}