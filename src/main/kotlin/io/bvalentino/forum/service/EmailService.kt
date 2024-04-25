package io.bvalentino.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notify(authorEmail: String) {
        val message = SimpleMailMessage()
        message.subject = "[Forum] Answer received!"
        message.text = "Hello, buddy! Your topic was answered! Let's go there to check it out?"
        message.from = "forum@email.com"
        message.setTo(authorEmail)

        javaMailSender.send(message)
    }

}
