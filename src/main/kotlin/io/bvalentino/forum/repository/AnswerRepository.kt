package io.bvalentino.forum.repository

import io.bvalentino.forum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long>