package io.bvalentino.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.springframework.security.core.GrantedAuthority

@Entity
class Role(
    val name: String,

    @ManyToMany(mappedBy = "roles")
    val users: MutableList<ForumUser> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : GrantedAuthority {

    override fun getAuthority(): String = name

}