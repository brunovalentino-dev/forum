package io.bvalentino.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Answer(
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val author: ForumUser,

    @ManyToOne
    val topic: Topic,

    val solution: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)