package io.bvalentino.forum.repository

import io.bvalentino.forum.model.ForumUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<ForumUser, Long> {

    fun findByEmail(username: String?): ForumUser?

}
