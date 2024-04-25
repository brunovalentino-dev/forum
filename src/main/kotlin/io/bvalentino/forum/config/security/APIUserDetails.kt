package io.bvalentino.forum.config.security

import io.bvalentino.forum.model.ForumUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class APIUserDetails(
    private val user: ForumUser
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = user.roles
    override fun getPassword(): String = user.password
    override fun getUsername(): String = user.email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

}
