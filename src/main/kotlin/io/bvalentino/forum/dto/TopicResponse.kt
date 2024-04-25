package io.bvalentino.forum.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.bvalentino.forum.model.TopicStatus
import java.io.Serializable
import java.time.LocalDateTime

data class TopicResponse(
    val id: Long?,
    val title: String,
    val message: String,

    @JsonProperty("topic_status")
    val topicStatus: TopicStatus,

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    val createdAt: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    val modifiedAt: LocalDateTime?
) : Serializable
