package io.bvalentino.forum.mapper

import io.bvalentino.forum.dto.TopicRegisterRequest
import io.bvalentino.forum.dto.TopicResponse
import io.bvalentino.forum.model.Topic
import io.bvalentino.forum.service.CourseService
import io.bvalentino.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicMapper(
    private val courseService: CourseService,
    private val userService: UserService
) {

    fun map(topic: Topic): TopicResponse {
        return TopicResponse(
            id = topic.id,
            title = topic.title,
            message = topic.message,
            topicStatus = topic.topicStatus,
            createdAt = topic.createdAt,
            modifiedAt = topic.modifiedAt
        )
    }

    fun map(topicRegisterRequest: TopicRegisterRequest): Topic {
        val foundCourse = topicRegisterRequest.courseId?.run {
            courseService.findCourse(this)
        }

        val foundAuthor = topicRegisterRequest.authorId?.run {
            userService.findUser(this)
        }

        return Topic(
            title = topicRegisterRequest.title!!,
            message = topicRegisterRequest.message!!,
            course = foundCourse!!,
            author = foundAuthor!!,
        )
    }

}
