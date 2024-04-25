package io.bvalentino.forum.model

object UserTest {

    fun build() = ForumUser(
        id = 1,
        name = "Teste",
        email = "teste@email.com",
        password = "{bcrypt}111222",
        roles = mutableListOf(
            Role(
                id = 1,
                name = "READ_WRITE",
                users = mutableListOf()
            )
        )
    )

}
