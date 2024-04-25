package io.bvalentino.forum.service

import io.bvalentino.forum.exception.NotFoundException
import io.bvalentino.forum.mapper.TopicMapper
import io.bvalentino.forum.model.Topic
import io.bvalentino.forum.model.TopicResponseTest
import io.bvalentino.forum.model.TopicTest
import io.bvalentino.forum.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import jakarta.persistence.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicServiceTest {

    private val foundTopics = PageImpl(listOf(TopicTest.build()))

    private val topicRepository: TopicRepository = mockk {
        every { findByCourseName(any(), any()) } returns foundTopics
        every { findAll(any<Pageable>()) } returns foundTopics
    }

    private val pageable: Pageable = mockk()

    private val topicMapper: TopicMapper = mockk {
        every { map(any<Topic>()) } returns TopicResponseTest.build()
    }

    private val topicService = TopicService(this.topicRepository, this.topicMapper)

    @Test
    fun `Should get topics by course name`() {
        topicService.getAll("Kotlin Web", this.pageable)

        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicMapper.map(any<Topic>()) }
        verify(exactly = 0) { topicRepository.findAll(pageable) }
    }

    @Test
    fun `Should get all topics when course name is null`() {
        topicService.getAll(null, this.pageable)

        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicMapper.map(any<Topic>()) }
        verify(exactly = 1) { topicRepository.findAll(pageable) }
    }

    @Test
    fun `Should throw an exception when a topic is not found`() {
        every { topicRepository.getReferenceById(any()) } throws EntityNotFoundException()

        val exception = assertThrows<NotFoundException> { topicService.getTopic(1) }

        assertThat(exception.message).isEqualTo("Topic not found in database! Please verify input data.")
    }

}