package io.bvalentino.forum.service

import io.bvalentino.forum.exception.NotFoundException
import io.bvalentino.forum.model.Course
import io.bvalentino.forum.repository.CourseRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CourseService(
    private val courseRepository: CourseRepository
) {

    fun findCourse(id: Long): Course {
        return try {
            courseRepository.getReferenceById(id)
        }
        catch (e: EntityNotFoundException) {
            throw NotFoundException("Course not found in database! Please verify input data.")
        }
    }

}
