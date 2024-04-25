package io.bvalentino.forum.mapper

import io.bvalentino.forum.dto.AnswerTopicRegisterRequest
import io.bvalentino.forum.model.Answer
import io.bvalentino.forum.service.TopicService
import io.bvalentino.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class AnswerMapper(
    private val topicService: TopicService,
    private val userService: UserService
) {

    fun map(answerTopicRegisterRequest: AnswerTopicRegisterRequest): Answer {
        val foundTopic = answerTopicRegisterRequest.topicId?.run {
            topicService.fetchTopic(this)
        }

        val foundAuthor = answerTopicRegisterRequest.authorId?.run {
            userService.findUser(this)
        }

        return Answer(
            message = answerTopicRegisterRequest.message!!,
            author = foundAuthor!!,
            topic = foundTopic!!,
        )
    }

}
