package io.bvalentino.forum.controller

import io.bvalentino.forum.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/reports")
class ReportController(
    private val topicService: TopicService
) {

    @GetMapping
    fun generateReport(model: Model): String {
        model.addAttribute("topicsByCourseCategory", topicService.getTopicsByCourseCategory())
        return "topic-report"
    }

}