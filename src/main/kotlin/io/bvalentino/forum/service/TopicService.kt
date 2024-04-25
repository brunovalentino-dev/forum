package io.bvalentino.forum.service

import io.bvalentino.forum.dto.TopicCourseCategory
import io.bvalentino.forum.dto.TopicRegisterRequest
import io.bvalentino.forum.dto.TopicResponse
import io.bvalentino.forum.dto.TopicUpdateRequest
import io.bvalentino.forum.exception.NotFoundException
import io.bvalentino.forum.mapper.TopicMapper
import io.bvalentino.forum.model.Topic
import io.bvalentino.forum.repository.TopicRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicMapper: TopicMapper
) {

    @Cacheable(cacheNames = ["topics"], key = "#root.method.name")
    fun getAll(courseName: String?, pagination: Pageable): Page<TopicResponse> {
        val foundTopics = courseName?.run {
            topicRepository.findByCourseName(this, pagination)
        }
        ?: topicRepository.findAll(pagination)

        return foundTopics.map {
            topic: Topic -> topicMapper.map(topic)
        }
    }

    fun getTopic(id: Long): TopicResponse {
        return try {
            val foundTopic = this.fetchTopic(id)

            foundTopic.run {
                topicMapper.map(this)
            }
        }
        catch (e: EntityNotFoundException) {
            throw NotFoundException("Topic not found in database! Please verify input data.")
        }
    }

    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun register(topicRegisterRequest: TopicRegisterRequest): TopicResponse {
        val newTopic = topicRepository.save(topicMapper.map(topicRegisterRequest))

        return topicMapper.map(newTopic)
    }

    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun modify(id: Long, topicUpdateRequest: TopicUpdateRequest): TopicResponse {
        return try {
            topicRepository.getReferenceById(id).run {
                this.title = topicUpdateRequest.title!!
                this.message = topicUpdateRequest.message!!
                this.modifiedAt = LocalDateTime.now()

                topicMapper.map(this)
            }
        }
        catch (e: EntityNotFoundException) {
            throw NotFoundException("Topic not found in database! Please verify input data.")
        }
    }

    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun remove(id: Long) {
        topicRepository.deleteById(id)
    }

    fun getTopicsByCourseCategory(): List<TopicCourseCategory> {
        return topicRepository.findTopicsByCourseCategory()
    }

    fun fetchTopic(id: Long): Topic {
        return topicRepository.getReferenceById(id)
    }

}
