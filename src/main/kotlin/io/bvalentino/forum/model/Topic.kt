package io.bvalentino.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Topic(
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime? = null,

    @ManyToOne
    val course: Course,

    @ManyToOne
    val author: ForumUser,

    @Enumerated(value = EnumType.STRING)
    val topicStatus: TopicStatus = TopicStatus.NOT_ANSWERED,

    @OneToMany(targetEntity = Answer::class, mappedBy = "topic")
    val answers: List<Answer> = ArrayList(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)