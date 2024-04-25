package io.bvalentino.forum.repository

import io.bvalentino.forum.dto.TopicCourseCategory
import io.bvalentino.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findByCourseName(courseName: String, pagination: Pageable): Page<Topic>

    @Query(
        """
            SELECT NEW io.bvalentino.forum.dto.TopicCourseCategory(course.category, count(t)) FROM Topic t
            JOIN t.course course GROUP BY course.category
        """
    )
    fun findTopicsByCourseCategory(): List<TopicCourseCategory>

}
