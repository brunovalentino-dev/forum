package io.bvalentino.forum.controller

import io.bvalentino.forum.dto.AnswerTopicRegisterRequest
import io.bvalentino.forum.service.AnswerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearerAuth")
class AnswerController(
    private val answerService: AnswerService
) {

    @PostMapping
    fun registerAnswer(
        @Valid @RequestBody answerTopicRegisterRequest: AnswerTopicRegisterRequest
    ) = answerService.register(answerTopicRegisterRequest)

}