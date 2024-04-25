package io.bvalentino.forum.controller

import io.bvalentino.forum.dto.TopicRegisterRequest
import io.bvalentino.forum.dto.TopicResponse
import io.bvalentino.forum.dto.TopicUpdateRequest
import io.bvalentino.forum.service.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
class TopicController(
    private val topicService: TopicService
) {

    @GetMapping
    fun getAllTopics(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pagination: Pageable
    ): ResponseEntity<Page<TopicResponse>> {
        return ResponseEntity.ok(topicService.getAll(courseName, pagination))
    }

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long): ResponseEntity<TopicResponse> {
        return ResponseEntity.ok(topicService.getTopic(id))
    }

    @PostMapping
    fun registerTopic(
        @Valid @RequestBody topicRegisterRequest: TopicRegisterRequest,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicResponse> {
        val registeredTopic = topicService.register(topicRegisterRequest)
        val uri = uriComponentsBuilder.path("/topics/${registeredTopic.id}")
            .build()
            .toUri()

        return ResponseEntity.created(uri).body(registeredTopic)
    }

    @PutMapping("/{id}")
    fun modifyTopic(
        @PathVariable id: Long,
        @Valid @RequestBody topicUpdateRequest: TopicUpdateRequest
    ): ResponseEntity<TopicResponse> {
        return ResponseEntity.ok(topicService.modify(id, topicUpdateRequest))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeTopic(@PathVariable id: Long) {
        topicService.remove(id)
    }

}