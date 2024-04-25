package io.bvalentino.forum.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicRegisterRequest(
    @field:NotBlank(message = "O campo título não pode estar em branco!")
    @field:Size(min = 5, max = 100, message = "O campo título deve conter entre 5 e 100 caracteres.")
    val title: String?,

    @field:NotBlank(message = "O campo mensagem não pode estar em branco!")
    val message: String?,

    @field:NotNull(message = "O campo id_curso não pode estar nulo!")
    @JsonProperty("course_id")
    val courseId: Long?,

    @field:NotNull(message = "O campo id_autor não pode estar nulo!")
    @JsonProperty("author_id")
    val authorId: Long?
)
