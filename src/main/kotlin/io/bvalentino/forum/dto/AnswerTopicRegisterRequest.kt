package io.bvalentino.forum.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AnswerTopicRegisterRequest(
    @field:NotBlank(message = "O campo mensagem não pode estar em branco!")
    val message: String?,

    @field:NotNull(message = "O campo id_autor não pode estar nulo!")
    @JsonProperty("author_id")
    val authorId: Long?,

    @field:NotNull(message = "O campo id_autor não pode estar nulo!")
    @JsonProperty("topic_id")
    val topicId: Long?
)
