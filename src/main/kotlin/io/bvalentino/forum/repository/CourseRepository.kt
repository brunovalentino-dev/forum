package io.bvalentino.forum.repository

import io.bvalentino.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long>
