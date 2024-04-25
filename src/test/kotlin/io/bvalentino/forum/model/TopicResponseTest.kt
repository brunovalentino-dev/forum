package io.bvalentino.forum.model

import io.bvalentino.forum.dto.TopicResponse
import java.time.LocalDateTime

object TopicResponseTest {

    fun build() = TopicResponse(
        id = 1,
        title = "Kotlin Spring Boot",
        message = "I dunno... :(",
        topicStatus = TopicStatus.NOT_ANSWERED,
        createdAt = LocalDateTime.now(),
        modifiedAt = LocalDateTime.now().plusMinutes(30)
    )

}