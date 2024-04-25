package io.bvalentino.forum.model

object TopicTest {

    fun build() = Topic(
        id = 1,
        title = "Kotlin OOP",
        message = "I dunno... :(",
        course = CourseTest.build(),
        author = UserTest.build()
    )

}