package io.bvalentino.forum.integration

import io.bvalentino.forum.config.DatabaseContainerConfig
import io.bvalentino.forum.dto.TopicCourseCategory
import io.bvalentino.forum.model.Topic
import io.bvalentino.forum.model.TopicTest
import io.bvalentino.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest : DatabaseContainerConfig() {

    @Autowired
    private lateinit var topicRepository: TopicRepository

    private val topic: Topic = TopicTest.build()

    @Test
    fun `Should generate a report showing topics by course category`() {
        topicRepository.save(topic)

        val report = topicRepository.findTopicsByCourseCategory()

        assertThat(report).isNotNull
        assertThat(report.first()).isExactlyInstanceOf(TopicCourseCategory::class.java)
    }

    @Test
    fun `Should get topics by course name`() {
        topicRepository.save(topic)

        val foundTopic = topicRepository.findByCourseName(topic.course.name, PageRequest.of(0, 5))

        assertThat(foundTopic).isNotNull
    }

}