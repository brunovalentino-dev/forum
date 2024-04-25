package io.bvalentino.forum.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class TopicUpdateRequest(
    @field:NotBlank @field:Size(min = 5, max = 100) val title: String?,
    @field:NotBlank val message: String?
)