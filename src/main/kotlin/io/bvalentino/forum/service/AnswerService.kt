package io.bvalentino.forum.service

import io.bvalentino.forum.dto.AnswerTopicRegisterRequest
import io.bvalentino.forum.mapper.AnswerMapper
import io.bvalentino.forum.repository.AnswerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val answerMapper: AnswerMapper,
    private val emailService: EmailService
) {

    @Transactional
    fun register(answerTopicRegisterRequest: AnswerTopicRegisterRequest) {
        val registeredAnswer = answerRepository.save(answerMapper.map(answerTopicRegisterRequest))
        val authorEmail = registeredAnswer.topic.author.email

        emailService.notify(authorEmail)
    }

}
