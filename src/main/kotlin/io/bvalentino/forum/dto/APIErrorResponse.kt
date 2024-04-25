package io.bvalentino.forum.dto

import java.time.LocalDateTime

data class APIErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)